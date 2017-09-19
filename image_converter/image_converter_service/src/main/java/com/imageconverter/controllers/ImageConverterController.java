package com.imageconverter.controllers;

import com.imageconverter.models.ImageModel;
import com.imageconverter.services.ImageConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImageConverterController {

    /**
     * POST /convert-image  --> Converts image to desirable type.
     */

    @RequestMapping(value = "/convert-image", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> convertImage(@RequestBody ImageModel imageToConvert, @RequestParam(value = "typeToConvert") String typeToConvert){

        return imageConverterService.convertImage(imageToConvert, typeToConvert);
    }

    @Autowired
    private ImageConverterService imageConverterService;

}
