package com.imageconverter.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imageconverter.models.ImageModel;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.imageconverter.consts.RestConst.*;
import static org.junit.Assert.assertEquals;

public class ImageConverterSteps {

    RestTemplate restTemplate = new RestTemplate();
    ImageModel image;
    ObjectMapper mapper = new ObjectMapper();


    @Given("getting image to convert with id $id")
    public void gettingImageToConvert(@Named("id")String id) throws IOException {
        String imageJson = restTemplate.getForObject(captureImageHost + getById + idParameter, String.class, id);
        image = mapper.readValue(imageJson, ImageModel.class);
    }

    @When("I am converting image to type $type")
    public void convertingImage(@Named("type")String type){
        image.setImageType(type.toUpperCase());
    }

    @Then("I am seeing image with type $type")
    public void checkingTypeOfTheImage(@Named("type")String type){
        assertEquals("Expected " + type.toUpperCase() + " but got "+ image.getImageType().toString(), type.toUpperCase(), image.getImageType().toString());
    }

}
