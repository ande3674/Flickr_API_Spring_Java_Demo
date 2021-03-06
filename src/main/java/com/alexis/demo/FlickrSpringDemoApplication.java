package com.alexis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@SpringBootApplication
@Controller
public class FlickrSpringDemoApplication {

    @RequestMapping("/")
    String home() {
        return "index.html";
    }

	public static void main(String[] args) {

		SpringApplication.run(FlickrSpringDemoApplication.class, args);


        /*
        * SOME TESTING STUFF - IGNORE FOR FINAL PROGRAM !
        * */

		//String link = String.format("http://farm%d.staticflickr.com/%s/%s_%s.jpg", 2, "server", "id", "secret");
        //System.out.println(link);
        //ArrayList<String> links = FlickrClient.searchByTag("paris france");
        //for (int i = 0 ; i < links.size() ; i++){
            //System.out.println(links.get(i));
        //}
	}
}
