package com.imageconverter.models;

public enum ImageType {

    JPG("jpg"),
    PNG("png"),
    JPEG("jpeg");

    private String type;

    public String getType() {
        return type;
    }

    ImageType(String type){
        this.type = type;
    }

}
