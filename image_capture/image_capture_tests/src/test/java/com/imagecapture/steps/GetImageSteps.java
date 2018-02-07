package com.imagecapture.steps;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imagecapture.models.ImageModel;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.imagecapture.consts.RequestConsts.*;
import static com.imagecapture.consts.RequestConsts.converterMicroserviceHost;
import static org.junit.Assert.assertEquals;


public class GetImageSteps {

    RestTemplate restTemplate = new RestTemplate();
    ImageModel image = new ImageModel();
    ObjectMapper mapper = new ObjectMapper();
    HttpHeaders headers = new HttpHeaders();

    @Given("Existing Image in DB with name $name and source url $url")
    public void givenImage(@Named("name") String name, @Named("url") String url) throws IOException {
        createNewImage(name, url);
    }

    @When("I am getting image with name $name")
    public void gettingImageWithName(@Named("name") String name) throws IOException {
        String imageJsonObj = restTemplate.getForObject(localHost + getByName + nameParameter, String.class, name);
        image =  mapper.readValue(imageJsonObj, ImageModel.class);
    }

    @Then("I am seeing image with name $name")
    public void checkingImageForName(@Named("name") String name){
        assertEquals("Expected " + name + " but got "+ image.getName(), name, image.getName());
    }

    public void createNewImage(@Named("name")String name, @Named("url")String url) throws IOException {
        //TODO add service for creating json document
        image.setName(name);
        image.setUrl(url);
        String jsonInString = mapper.writeValueAsString(image);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(jsonInString, headers);
        String imageJsonObj = restTemplate.postForObject(localHost + capture, entity, String.class);
        image = mapper.readValue(imageJsonObj, ImageModel.class);
    }

    @When("I am sending image with name $name to convert to type $type")
    public void convertImage(@Named("name")String name, @Named("type") String type) throws IOException {
        String imageJsonObj = restTemplate.getForObject(localHost + getByName + nameParameter, String.class, name);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(imageJsonObj, headers);
        String convertedImageJsonObj = restTemplate.postForObject(converterMicroserviceHost + convertImage + typeToConvert, entity, String.class, type);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        image =  mapper.readValue(convertedImageJsonObj, ImageModel.class);
    }

    @Then("I am seeing image with type $type")
    public void checkingTypeOfTheImage(@Named("type") String type){
        assertEquals("Expected type " + type + " but got "+ image.getUrl().substring(image.getUrl().lastIndexOf(".") + 1).trim(),
                type, image.getUrl().substring(image.getUrl().lastIndexOf(".") + 1).trim());
    }
}
