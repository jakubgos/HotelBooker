package com.jgos.hotelbooker.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    byte[] getImage(String image);

    boolean store(UserDetails userDetails, MultipartFile file);

    String getCurrentMainPicturePath(String userName);
}
