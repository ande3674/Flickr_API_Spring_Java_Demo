package com.alexis.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Flickr Controller class, handles the webpage display stuff
@RestController
public class FlickrController {

    private static String URL_GET_RECENT = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent" +
            "&api_key=41014e55b2a616bc84ba31aa9c8966cb&per_page=10&format=json&nojsoncallback=1";
    private static String URL_GET_PARIS = "https://api.flickr.com/services/rest/?method=flickr.photos.search" +
            "&api_key=41014e55b2a616bc84ba31aa9c8966cb&tags=paris&format=json&nojsoncallback=1";


    @RequestMapping("/stuff")
    ResponseEntity<String> displayStuff(){
        String jsonString = FlickrClient.get();
        return new ResponseEntity<>(jsonString, HttpStatus.OK);

    }

    @RequestMapping("/obj")
    ResponseEntity<Flickr> firstObj(){
        Flickr flickr = FlickrClient.getOneFlickrJsonPhoto(URL_GET_RECENT);
        return new ResponseEntity<>(flickr, HttpStatus.OK);
    }

    @RequestMapping("/paris")
    ResponseEntity<Flickr> parisObj(){
        Flickr flickr = FlickrClient.getOneFlickrJsonPhoto(URL_GET_PARIS);
        return new ResponseEntity<>(flickr, HttpStatus.OK);
    }
}
