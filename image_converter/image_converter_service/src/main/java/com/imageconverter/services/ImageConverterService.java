package com.imageconverter.services;

import com.imageconverter.models.ImageModel;
import com.imageconverter.models.ImageType;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<?> convertImage(ImageModel image, String newType) {

        image.setImageType(returnImageType(image.getUrl()));

        if (EnumUtils.isValidEnum(ImageType.class, newType.toUpperCase()) && (!image.getImageType().toString().equals(newType))) {
            return new ResponseEntity<ImageModel>(setConvertedImageType(image, newType), HttpStatus.OK);
        } else {
            logger.debug("Wrong image type was sent ( or same type as image already has )");
            return new ResponseEntity<ImageModel>(image, HttpStatus.NO_CONTENT);
        }
    }

    private ImageModel setConvertedImageType(ImageModel image, String newType){

        StringBuilder builder = new StringBuilder(image.getUrl());
        Integer dotPosition = image.getUrl().lastIndexOf(".");
//        builder.replace(dotPosition + 1, image.getUrl().length(), newType);
        builder.replace(dotPosition + 1, image.getUrl().length(), "jpeg");
        image.setUrl(builder.toString());
        image.setImageType(ImageType.valueOf(newType.toUpperCase()));
        return image;
    }

    private ImageType returnImageType(String url){

        String type = url.substring(url.lastIndexOf(".") + 1).trim();
        return ImageType.valueOf(type.toUpperCase());
    }
}
