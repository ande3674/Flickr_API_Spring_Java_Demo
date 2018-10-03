package com.alexis.demo;

// Standard Flickr Object class that uses the information returned from the API about a photo
public class Flickr {
    // Fields returned by API can be class attributes/properties
    private String owner;
    private String server;
    private int ispublic;
    private int isfriend;
    private int farm;
    private String id;
    private String secret;
    private String title;
    private int isfamily;

    // Auto Generated Constructor, Getters & Setters, toString
    public Flickr() {}

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int isIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int isIsfriend() {
        return isfriend;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int isIsfamily() {
        return isfamily;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    @Override
    public String toString() {
        return "Flickr{" +
                "owner='" + owner + '\'' +
                ", server='" + server + '\'' +
                ", ispublic=" + ispublic +
                ", isfriend=" + isfriend +
                ", farm=" + farm +
                ", id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                ", title='" + title + '\'' +
                ", isfamily=" + isfamily +
                '}';
    }
}
