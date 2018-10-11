package com.alexis.demo;

import java.sql.*;

// Flickr Database Class, handles all of the database stuff
public class FlickrDatabase {

    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static String DB_CONNECTION_URL = "jdbc:mysql://localhost/FlickrDB?useLegacyDatetimeCode=false&serverTimezone=America/Chicago";
    private static String USER = "quick"; //replace with your username
    private static String PASSWORD = "flick"; // replace with your password

    private static String TABLE = "photos";
    private static String PK_COLUMN = "key"; //
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

    private static Statement statement = null;
    private static Connection conn = null;

    FlickrDatabase() {



        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check if you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        //create the connection and statement object for running queries
        try {
            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException se) {
            se.printStackTrace();
        }

        createTable();

    }

    private void createTable() {

        try {
            //You should have already created a database via terminal/command prompt

            //Create a table in the database, if it does not exist already
            //Can use String formatting to build this type of String from constants coded in your program
            //Don't do this with variables with data from the user!! That's what ParameterisedStatements are, and that's for queries, updates etc. , not creating tables.
            // You shouldn't make database schemas from user input anyway.
            String createPhotosTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s VARCHAR (200), %s VARCHAR (200), %s VARCHAR (200), " +
                    "%s int, %s int, %s int, %s VARCHAR (200), %s VARCHAR (200), %s VARCHAR (200), %s int, PRIMARY KEY (%s))";
            String createPhotosTableSQL = String.format(createPhotosTableSQLTemplate, TABLE, PK_COLUMN, PHOTO_NAME_COLUMN, OWNER_COLUMN, SERVER_COLUMN,
                    ISPUBLIC_COLUMN, ISFRIEND_COLUMN, FARM_COLUMN, ID_COLUMN, SECRET_COLUMN, TITLE_COLUMN, ISFAMILY_COLUMN, PK_COLUMN);


            statement.executeUpdate(createPhotosTableSQL);
            System.out.println("Created photos table");


        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    // TODO Figure out DB details and create all DB sql statements, update, select, update, etc methods
    private void addRow(String name, String owner, String server, int isPublic, int isFriend, int farm, String id, String secret, String title, int  isFamily){

        try {

            String insertPhotoSQLTemplate = "INSERT INTO % VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)";
            String insertPhotoSQL = String.format(insertPhotoSQLTemplate, TABLE, PHOTO_NAME_COLUMN, OWNER_COLUMN, SERVER_COLUMN,
                    ISPUBLIC_COLUMN, ISFRIEND_COLUMN, FARM_COLUMN, ID_COLUMN, SECRET_COLUMN, TITLE_COLUMN, ISFAMILY_COLUMN);

            System.out.println("The SQL for the prepared statement is " + insertPhotoSQL);
            PreparedStatement insertPhoto = conn.prepareStatement(insertPhotoSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            insertPhoto.setString(1, name);
            insertPhoto.setString(2, owner);
            insertPhoto.setString(3, server);
            insertPhoto.setInt(4, isPublic);
            insertPhoto.setInt(5, isFriend);
            insertPhoto.setInt(6, farm);
            insertPhoto.setString(7, id);
            insertPhoto.setString(8, secret);
            insertPhoto.setString(9, title);
            insertPhoto.setInt(10, isFamily);
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(insertPhoto.toString());

            insertPhoto.executeQuery();

            System.out.println("Added photo");


        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    private void updateName(String newName, String oldName){

        try {

            String updatePhotoTemplate = "UPDATE PHOTOS SET % = ? WHERE % = ?";
            String updatePhotoSQL = String.format(updatePhotoTemplate, PHOTO_NAME_COLUMN, PHOTO_NAME_COLUMN);

            System.out.println("The SQL for the prepared statement is " + updatePhotoSQL);
            PreparedStatement updatePhoto = conn.prepareStatement(updatePhotoSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            updatePhoto.setString(1, newName);
            updatePhoto.setString(2, oldName);


            updatePhoto.executeUpdate();
            System.out.println("Updated photo");


        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
