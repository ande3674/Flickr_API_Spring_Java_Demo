package com.alexis.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Flickr Controller class, handles the webpage display stuff
@RestController
public class FlickrController {

    @RequestMapping("/")
    ResponseEntity<String> displayStuff(){
        String jsonString = FlickrClient.get();
        return new ResponseEntity<>(jsonString, HttpStatus.OK);

    }

    @RequestMapping("/obj")
    ResponseEntity<Flickr> firstObj(){
        Flickr flickr = FlickrClient.getOneFlickrObject();
        return new ResponseEntity<>(flickr, HttpStatus.OK);
    }
}
