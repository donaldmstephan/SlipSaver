package com.dartmouth.cs.slipsaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Donald on 3/1/2016.
 */
public class ContactDBHelper extends SQLiteOpenHelper {
    public static final String ENTRIES = "entry";
    public static final String KEY_ROWID = "key_id";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String EMAIL = "email";
    public static final String SELECTED = "selected";

    private static final String DATABASE_NAME = "contactdb";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE =
            "CREATE TABLE IF NOT EXISTS "
                    + ENTRIES
                    + " ( "
                    + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FIRSTNAME
                    + " STRING, "
                    + LASTNAME
                    + " STRING, "
                    + EMAIL
                    + " STRING, "
                    + SELECTED
                    + " INTEGER " + ");";

    // Constructor
    public ContactDBHelper(Context context) {
        // DATABASE_NAME is, of course the name of the database, which is defined as a tring constant
        // DATABASE_VERSION is the version of database, which is defined as an integer constant
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table schema if not exists
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE);
    }

    // Insert a item given each column value
    public long insertEntry(Contact entry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIRSTNAME, entry.FirstName);
        values.put(LASTNAME, entry.LastName);
        values.put(EMAIL, entry.Email);
        values.put(SELECTED, entry.Selected);

        long result = db.insert(ENTRIES, null, values);
        db.close();
        Log.d("DB", "added");

        return result;

    }

    // Remove an entry by giving its index
    public void removeEntry(long rowIndex) {
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            db.delete(ENTRIES, KEY_ROWID + " = ?", new String[]{String.valueOf(rowIndex)});
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.close();
    }

    // Query a specific entry by its index.
    public Contact fetchEntryByIndex(long rowId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ENTRIES, new String[] { KEY_ROWID,
                        FIRSTNAME, LASTNAME, EMAIL, SELECTED }, KEY_ROWID + "=?",
                new String[] { String.valueOf(rowId) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Contact indexEntry = new Contact(cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getLong(0), cursor.getInt(4));

        return indexEntry;
    }

    // Query the entire table, return all rows
    public ArrayList<Contact> fetchEntries() {
        ArrayList<Contact> eeList = new ArrayList<Contact>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ENTRIES, null);

        if (cursor.moveToFirst() != false) {
            Contact indexEntry = new Contact(cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getLong(0),cursor.getInt(4));
            eeList.add(indexEntry);
        }

        while (cursor.moveToNext() != false) {
            Contact indexEntry = new Contact(cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getLong(0),cursor.getInt(4));
            eeList.add(indexEntry);
        }

        cursor.getCount();
        return (eeList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all data");
        db.execSQL("DROP TABLE IF EXISTS ");

        onCreate(db);
    }

    public String getName() {
        return DATABASE_NAME;
    }
}
