package com.alexis.demo;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.sun.deploy.net.HttpResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;

// Flickr API Class handles API stuff
public class FlickrClient {

    // Flickr Access Links & Info
    private static String URL_GET_RECENT = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent" +
            "&api_key=41014e55b2a616bc84ba31aa9c8966cb&per_page=10&format=json&nojsoncallback=1";
    private static String URL_GET_PARIS = "https://api.flickr.com/services/rest/?method=flickr.photos.search" +
            "&api_key=41014e55b2a616bc84ba31aa9c8966cb&tags=paris&format=json&nojsoncallback=1";
    private static String SECRET = "ec70c827417a7332";
    private static String KEY = "41014e55b2a616bc84ba31aa9c8966cb";

    // TEMPLATE FOR A URL FOR A FETCHED PHOTO !
    // farm, server, id, secret
    private static String photoUrlTemplate = "http://farm%d.staticflickr.com/%s/%s_%s.jpg";
    // Some working examples:
    private static String photoUrl1 = "http://farm2.staticflickr.com/1955/30131952017_b057dd127b.jpg";
    private static String photoUrl2 = "http://farm2.staticflickr.com/1969/30132175597_be81e484a5.jpg";

    public FlickrClient(){}

    public static String get(){
        try {
            HttpResponse<JsonNode> response = Unirest.get(URL_GET_RECENT).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            return jsonObject.toString();
        } catch (UnirestException ue){
            System.out.println(ue);
            return null;
        }
    }

    public static Flickr getOneFlickrJsonPhoto(String URL){

        try {
            HttpResponse<JsonNode> response = Unirest.get(URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            // Here we can parse the JSON data and add things to our database
            JSONObject jsonPhotosObject = jsonObject.getJSONObject("photos");
            JSONArray jsonPhotoArray = jsonPhotosObject.getJSONArray("photo");
            JSONObject firstPhotoJsonObject = jsonPhotoArray.getJSONObject(0);

            // Create a new Flickr Object
            Flickr flickr = new Flickr();
            flickr.setOwner(firstPhotoJsonObject.getString("owner"));
            flickr.setServer(firstPhotoJsonObject.getString("server"));
            flickr.setIspublic(firstPhotoJsonObject.getInt("ispublic"));
            flickr.setIsfriend(firstPhotoJsonObject.getInt("isfriend"));
            flickr.setFarm(firstPhotoJsonObject.getInt("farm"));
            flickr.setId(firstPhotoJsonObject.getString("id"));
            flickr.setSecret(firstPhotoJsonObject.getString("secret"));
            flickr.setTitle(firstPhotoJsonObject.getString("title"));
            flickr.setIsfamily(firstPhotoJsonObject.getInt("isfamily"));

            return  flickr;
        }
        catch (UnirestException ue){
            System.out.println(ue);
            return null;
        }
    }

    public static Flickr[] getManyFlickrJsonPhoto(String URL){

        try {
            HttpResponse<JsonNode> response = Unirest.get(URL).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            // Here we can parse the JSON data and add things to our database
            JSONObject jsonPhotosObject = jsonObject.getJSONObject("photos");
            JSONArray jsonPhotoArray = jsonPhotosObject.getJSONArray("photo");
            Flickr[] flickrArray = new Flickr[10];

            for (int i = 0 ; i < 10 ; i++) {
                JSONObject jsonPhotoObject = jsonPhotoArray.getJSONObject(i);

                // Create a new Flickr Object
                // Loop for all 10 photos
                Flickr flickr = new Flickr();
                flickr.setOwner(jsonPhotoObject.getString("owner"));
                flickr.setServer(jsonPhotoObject.getString("server"));
                flickr.setIspublic(jsonPhotoObject.getInt("ispublic"));
                flickr.setIsfriend(jsonPhotoObject.getInt("isfriend"));
                flickr.setFarm(jsonPhotoObject.getInt("farm"));
                flickr.setId(jsonPhotoObject.getString("id"));
                flickr.setSecret(jsonPhotoObject.getString("secret"));
                flickr.setTitle(jsonPhotoObject.getString("title"));
                flickr.setIsfamily(jsonPhotoObject.getInt("isfamily"));

            }

            return  flickrArray;
        }
        catch (UnirestException ue){
            System.out.println(ue);
            return null;
        }
    }

    /*
    public static Flickr getOneFlickrObject(){

        try {
            HttpResponse<JsonNode> response = Unirest.get(URL_GET_RECENT).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            // Here we can parse the JSON data and add things to our database
            JSONObject jsonPhotosObject = jsonObject.getJSONObject("photos");
            JSONArray jsonPhotoArray = jsonPhotosObject.getJSONArray("photo");
            JSONObject firstPhotoJsonObject = jsonPhotoArray.getJSONObject(0);

            // Create a new Flickr Object
            Flickr flickr = new Flickr();
            flickr.setOwner(firstPhotoJsonObject.getString("owner"));
            flickr.setServer(firstPhotoJsonObject.getString("server"));
            flickr.setIspublic(firstPhotoJsonObject.getInt("ispublic"));
            flickr.setIsfriend(firstPhotoJsonObject.getInt("isfriend"));
            flickr.setFarm(firstPhotoJsonObject.getInt("farm"));
            flickr.setId(firstPhotoJsonObject.getString("id"));
            flickr.setSecret(firstPhotoJsonObject.getString("secret"));
            flickr.setTitle(firstPhotoJsonObject.getString("title"));
            flickr.setIsfamily(firstPhotoJsonObject.getInt("isfamily"));

            return  flickr;
        }
        catch (UnirestException ue){
            System.out.println(ue);
            return null;
        }
    }

    public static Flickr getOneParisFlickrObject(){

        try {
            HttpResponse<JsonNode> response = Unirest.get(URL_GET_PARIS).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            // Here we can parse the JSON data and add things to our database
            JSONObject jsonPhotosObject = jsonObject.getJSONObject("photos");
            JSONArray jsonPhotoArray = jsonPhotosObject.getJSONArray("photo");
            JSONObject firstPhotoJsonObject = jsonPhotoArray.getJSONObject(0);

            // Create a new Flickr Object
            Flickr flickr = new Flickr();
            flickr.setOwner(firstPhotoJsonObject.getString("owner"));
            flickr.setServer(firstPhotoJsonObject.getString("server"));
            flickr.setIspublic(firstPhotoJsonObject.getInt("ispublic"));
            flickr.setIsfriend(firstPhotoJsonObject.getInt("isfriend"));
            flickr.setFarm(firstPhotoJsonObject.getInt("farm"));
            flickr.setId(firstPhotoJsonObject.getString("id"));
            flickr.setSecret(firstPhotoJsonObject.getString("secret"));
            flickr.setTitle(firstPhotoJsonObject.getString("title"));
            flickr.setIsfamily(firstPhotoJsonObject.getInt("isfamily"));

            return  flickr;
        }
        catch (UnirestException ue){
            System.out.println(ue);
            return null;
        }
    }
    */
}
