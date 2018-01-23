package com.example.orinamokaya.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String KEY_DELICACY = "food_delicacy"; // this will hold the delicacy of the food entered into the database


    // Setting up the database
    // private data
    private static final String DATABASE_NAME = "DeliciousOrNot"; // databse name
    private static final String DATABASE_TABLE = "foodTable"; // table name. it'll contain the rows above defined
    private static final int DATABASE_VERSION = 1; //database version

    private DbHelper ourHelper; //instanceof the DbHelper class
    private final Context ourContext; // context of the current class DeliciousOrNor
    private SQLiteDatabase  ourDatabase; //our SQL databse



    //this class will help build the database. we don't want to do this in the user interface thread.
    private static class DbHelper extends SQLiteOpenHelper{

        //class constructor.  the other variables are ommitted because we don't need them now. we will use the default. hereare the omitted vars String name, SQLiteDatabase.CursorFactory factory, int version
        public DbHelper(Context context) {
            //using default values fo the
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        //only called once when the database is created for the first time
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            //execute SQL code to set up the database
            sqLiteDatabase.execSQL( "CREATE TABLE " + DATABASE_TABLE + " (" +
                   KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // COLUMN 1
                    KEY_NAME + " TEXT NOT NULL, " + // COLUMN 2
                    KEY_DELICACY + " TEXT NOT NULL);" // COLUMN 3
            );
        }

        //will be called each time the database iS updated  ""
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            // iF the tables exist, they will be deleted
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(sqLiteDatabase); //and created afresh
        }
    }

    //this is a constructor for the DeliciousOrNot class
    //setting up the context variable by getting the current context and assigning it to the privat variable ourContext  for us to work with
    //this context will be used in the DbHelper class
    public DeliciousOrNot(Context c){
        ourContext = c;
    }

    //method that we'll use publicly to open the datbase from another class
    public DeliciousOrNot open() throws SQLException{ // because we are catching an exception in the update button, so that didItWoek  can be set to false and do nothing
        //pass in the context of what is being passed into the DeliciousOrNot class
        ourHelper = new DbHelper(ourContext); // this will set up all database stuff because it calls the DbHelper class we just created. refere to the constructor DbHelper class
        ourDatabase = ourHelper.getWritableDatabase(); // open the database and now you can write to it
        return this;
    }

    //close the database... i.e close the DbHelper class
    public void close(){
        ourHelper.close();
    }

    //This method will be used to enter data into the database
    public long createEntry(String name, String delicious) {
        // data is inserted into the database through ContentValues
        ContentValues cv = new ContentValues(); // will be used to insert data into to the database
        // add information into the contentValue
        cv.put(KEY_NAME,name);  // KEY is the column name [food_name] and value is the data to be put into that column [name]
        cv.put(KEY_DELICACY, delicious); // KEY is the column name [food_delicacy] and value is the data to be put into that column [delicious]
        return ourDatabase.insert(DATABASE_TABLE,null,cv); // insert into the database that we created in the DeliciousOrNot open method
    }
}
