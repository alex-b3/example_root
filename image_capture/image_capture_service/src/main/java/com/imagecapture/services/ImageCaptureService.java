package com.imagecapture.services;


import com.imagecapture.models.ImageModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class ImageCaptureService {

    ArrayList<ImageModel> imageModelArrayList; // mock instead of DB

    public ImageCaptureService(){

        imageModelArrayList = new ArrayList<>();

    }

    public ResponseEntity<?> takeNewImage(ImageModel image) {

        if (newImageValidation(image)) {

            image.setId(imageModelArrayList.size());
            image.setDate(new Date());
            imageModelArrayList.add(image); // mock for adding data to DB
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Wrong data for the image was provided!!!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> getImageByName(String name){

        try{

            return new ResponseEntity<>(imageModelArrayList.stream().filter(p -> p.getName().equals(name)).findFirst().get(), HttpStatus.OK); // mock for looking object in DB
        }catch (Exception e){

            return new ResponseEntity<>("Image was no found!!!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<?> getImageById(long id){

        return new ResponseEntity<>(imageModelArrayList.stream().filter(p -> p.getId() == id).findFirst().get(), HttpStatus.OK); // mock for looking object in DB
    }

    private boolean newImageValidation(ImageModel image){
        boolean validImage = true;

        if(image.getUrl() == null){
            validImage = false;
        }
        return validImage;
    }
}

