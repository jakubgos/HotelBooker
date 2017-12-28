package com.jgos.hotelbooker.controller;

import com.jgos.hotelbooker.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ImageController {
    private static final Logger log = LoggerFactory.getLogger(ImageController.class);


    @Autowired
    private ImageService imageService;



    @RequestMapping(value = "/image/{image:.+\\..+}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable String image) {
        byte[] imageBytes = imageService.getImage(image);
        HttpStatus status = imageBytes.length > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(imageBytes);
    }


    @RequestMapping(value = "/gallery" ,  method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView upload(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, defaultValue = "0") int result) {
        ModelAndView model = new ModelAndView();
        model.setViewName("gallery");
        model.addObject("currentPicture",imageService.getCurrentMainPicturePath(userDetails.getUsername()));

        if (result == 1) {
            model.addObject("message", "Obraz zapisany.");
        }
        if (result == 2) {
            model.addObject("errorMessage", "Nie wybrano obrazu");
        }
        return model;
    }

    @RequestMapping(value = "/uploadSinglePicture" ,  method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveNotification(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("file") MultipartFile file )  {

        if (imageService.store(userDetails,file))
        return new ModelAndView("redirect:/gallery?result=1");
        else
        return new ModelAndView("redirect:/gallery?result=2");
    }


}
