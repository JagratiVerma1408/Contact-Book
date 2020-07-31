package com.example.contactbook;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts";
    private static final String TABLE_NAME = "Name";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHNO = "phno";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_ITEM_TABLE ="CREATE TABLE    " + TABLE_NAME + "( "
                + COLUMN_ID + "   INTEGER PRIMARY KEY, " + COLUMN_NAME + "   TEXT, " + COLUMN_EMAIL + "   TEXT, " + COLUMN_PHNO + "  TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void insertLabel(String label,String label1,String label2)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, label);
        values.put(COLUMN_EMAIL,label1);
        values.put(COLUMN_PHNO,label2);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<String> getAllLabels()
    {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM  " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
    List <Contact>  getAllContacts()
    {
        List<Contact> list = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM  " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do {
                Contact c= new Contact();
                c.setName(cursor.getString(1));
                c.setEmail(cursor.getString(2));
                c.setPhno(cursor.getString(3));
                list.add(c);
            } while (cursor.moveToNext());

        }
        // closing connection
        cursor.close();
        db.close();
        return list;

    }



}
