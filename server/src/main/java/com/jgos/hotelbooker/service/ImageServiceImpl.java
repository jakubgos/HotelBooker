package com.jgos.hotelbooker.service;

import com.jgos.hotelbooker.controller.ApiHotel;
import com.jgos.hotelbooker.entity.hotel.HotelDetail;
import com.jgos.hotelbooker.entity.hotel.ImagePath;
import com.jgos.hotelbooker.entity.user.UserDb;
import com.jgos.hotelbooker.repository.HotelDetailRepository;
import com.jgos.hotelbooker.repository.HotelRepository;
import com.jgos.hotelbooker.repository.ImageRepository;
import com.jgos.hotelbooker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageServiceImpl implements ImageService {

    private final static String CURRENT_PATH = "image";

    private final static String FRUL_PATH = "src/main/resources/images";
    private final Path rootLocation = Paths.get(FRUL_PATH);

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    HotelDetailRepository hotelDetailRepository;

    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public byte[] getImage(String image) {
        try {
            log.info("getImage() invoked with param image: " + image);

            Path file = rootLocation.resolve(image);
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists())
            {
                log.info("getImage resource not found, using default instead.");

                file = rootLocation.resolve("default.jpeg");
                resource = new UrlResource(file.toUri());
            }
            return IOUtils.toByteArray(resource.getInputStream());

        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("FAIL!");

    }

    @Override
    public boolean store(UserDetails userDetails, MultipartFile file) {

        UserDb user = userRepository.findByEmail(userDetails.getUsername());
        ImagePath imagePath = imageRepository.findByOwner(user);
        hotelRepository.findByOwner(user);

        HotelDetail hotelDetail = hotelRepository.findByOwner(user).getHotelDetail();

        if (file.isEmpty())
        {
            return false;
        }

        String fileName = userDetails.getUsername()+"_"+ file.getOriginalFilename();
        if (imagePath == null )
        {
            imagePath = new ImagePath(fileName,user);
        }
        else
        {
            imagePath.setPath(fileName);
        }

        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store picture!");
        }

        hotelDetail.setPicturePath(CURRENT_PATH+"\\"+ fileName);
        imageRepository.save(imagePath);
        hotelDetailRepository.save(hotelDetail);
        return true;
    }

    @Override
    public String getCurrentMainPicturePath(String userName) {
        UserDb user = userRepository.findByEmail(userName);
        ImagePath imagePath = imageRepository.findByOwner(user);
        if (imagePath == null)
        {
            return CURRENT_PATH+"\\default.jpeg";
        }
        return CURRENT_PATH + "\\"+ imagePath.getPath();
    }

}

