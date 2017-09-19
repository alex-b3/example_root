package com.imagecapture.controllers;


import com.imagecapture.models.ImageModel;
import com.imagecapture.services.ImageCaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ImageCaptureController {

    /**
     * POST /create  --> Create a new user and save it in the database.
     */
    @RequestMapping(value = "/capture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ImageModel image) throws IOException {

        return imageCaptureService.takeNewImage(image);
    }

    /**
     * GET /get-by-name  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/get-by-name")
    @ResponseBody
    public ResponseEntity<?> getByName(String name) {

        return imageCaptureService.getImageByName(name);
    }

    @RequestMapping("/get-by-id")
    @ResponseBody
    public ResponseEntity<?> getById(long id){

        return imageCaptureService.getImageById(id);
    }

    @Autowired
    ImageCaptureService imageCaptureService;
}
