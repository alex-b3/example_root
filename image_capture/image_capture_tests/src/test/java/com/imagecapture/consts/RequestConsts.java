package com.imagecapture.consts;

public class RequestConsts {

    private RequestConsts(){

    }

    public static final String localHost = "http://localhost:8080";
    public static final String getByName = "/get-by-name?";
    public static final String nameParameter = "name={name}&";
    public static final String capture = "/capture?";
    public static final String delete = "/delete?";
    public static final String converterMicroserviceHost = "http://localhost:8081";
    public static final String convertImage = "/convert-image?";
    public static final String typeToConvert = "typeToConvert={type}";

}
