package com.example.contactbook;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler1 extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contats";
    private static final String TABLE_NAME = "Log";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    public DatabaseHandler1(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_ITEM_TABLE ="CREATE TABLE    " + TABLE_NAME + "( "
                + COLUMN_USERNAME + "  TEXT , " + COLUMN_PASSWORD + "   TEXT)";
        db.execSQL(CREATE_ITEM_TABLE);
    }
    public String searchpass(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, d;
        d="not found";
        if (cursor.moveToFirst()) {
            do {
                a=cursor.getString(0);

                if (a.equals(username)){
                    d=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());

        }
        return d;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
        this.onCreate(db);

    }
    public void insertcontacts(Contact1 c)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASSWORD,c.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

}


