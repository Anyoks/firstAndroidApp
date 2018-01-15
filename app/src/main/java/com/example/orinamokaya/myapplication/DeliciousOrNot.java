package com.example.orinamokaya.myapplication;

/**
 * Created by root on 1/15/18.
 *
 *
 * this class will deal with th database connection etc
 */

public class DeliciousOrNot {

    // THESE ARE THE TABLE ROWS

    //public data

    public static final String KEY_ROWID = "_id"; // this will hold the row id of data entered into the database
    public static final String KEY_NAME = "food_name"; // this will hold the name of data entered into the database
    public static final String KEY_Delicacy = "food_delicacy"; // this will hold the delicacy of the food entered into the database


    // Setting up the database
    // private data
    private static final String DATABASE_NAME = "DeliciousOrNot"; // databse name
    private static final String DATABASE_TABLE = "foodTable"; // table name. it'll contain the rows above defined
    private static final int DATABASE_VERSION = 1; //database version

}
