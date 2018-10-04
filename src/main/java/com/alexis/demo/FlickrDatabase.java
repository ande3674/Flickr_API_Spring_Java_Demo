package com.alexis.demo;

// Flickr Database Class, handles all of the database stuff
public class FlickrDatabase {

    private static String CONNECTION_URL = ""; // TODO

    private static String PK_COLUMN = "key"; // auto-generate?
    private static String PHOTO_NAME_COLUMN = "name"; // User enters the name
    private static String OWNER_COLUMN = "owner";
    private static String SERVER_COLUMN = "server";
    private static String ISPUBLIC_COLUMN = "ispublic";
    private static String ISFRIEND_COLUMN = "isfriend";
    private static String FARM_COLUMN = "farm";
    private static String ID_COLUMN = "id";
    private static String SECRET_COLUMN = "secret";
    private static String TITLE_COLUMN = "title";
    private static String ISFAMILY_COLUMN = "isfamily";

    // CREATE TABLE SQL STATEMENT
    // CREATE TABLE photos_table

    // ADD ROW SQL STATEMENT
    // INSERT INTO photos_table VALUES (name, owner, server, ispub, isfri, farm, id, secret, title, isfam)
    private static String SQL_ADD_ROW = "";


    // TODO Figure out DB details and create all DB sql statements, update, select, update, etc methods
    private void addRow(String name, String owner, String server, String ispublic, String isfriend, String farm, String id, String secret, String title, String isfamily){
        // TODO
    }
}
