package com.alexis.demo;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.sun.deploy.net.HttpResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

// Flickr API Class handles API stuff
public class FlickrClient {

    public static String URL = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=41014e55b2a616bc84ba31aa9c8966cb&per_page=10&format=json&nojsoncallback=1";

    private static String URL_GET_RECENT = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=41014e55b2a616bc84ba31aa9c8966cb" +
            "&per_page=10&format=json&nojsoncallback=1";
    private static String SECRET = "ec70c827417a7332";
    private static String KEY = "41014e55b2a616bc84ba31aa9c8966cb";

    public FlickrClient(){}

    public static String get(){

        try {
            HttpResponse<JsonNode> response = Unirest.get(URL_GET_RECENT).header("accept", "application/json").asJson();
            JSONObject jsonObject = response.getBody().getObject();
            // TODO Here we can parse the JSON data and add things to our database
            return  jsonObject.toString(); // TODO this doesnt do anything useful right now - just for testing purposes
            /*

            ** JSON stuff from my old project - Needs to be tailored to Flickr JSON data **

            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject itemsObj = items.getJSONObject(0);
            JSONObject volInfoObj = itemsObj.getJSONObject("volumeInfo");

            */
        }
        catch (UnirestException ue){
            System.out.println(ue);
            return "ERROR!";
        }
    }

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
}
