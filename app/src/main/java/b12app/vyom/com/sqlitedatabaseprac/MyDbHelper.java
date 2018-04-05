package b12app.vyom.com.sqlitedatabaseprac;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {



    public static final String DB_NAME = "mydb";
    public static final String TABLE_NAME = "mytable";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final int VERSION = 2;
    public static final String ID = "id";


    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);

    }





    @Override
    public void onCreate(SQLiteDatabase db) {

//        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
//                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + USERNAME + " TEXT,"
//                + PASSWORD + " TEXT,"
//                + EMAIL + " TEXT"
//                + ")";
//
//        db.execSQL(CREATE_TABLE);

        db.execSQL("create table " + TABLE_NAME +" (id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);


    }


}
