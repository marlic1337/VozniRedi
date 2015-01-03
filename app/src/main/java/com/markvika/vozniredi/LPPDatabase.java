package com.markvika.vozniredi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Created by Vika Lampret on 31.12.2014.
 */
public class LPPDatabase extends SQLiteOpenHelper {

    public static final String TABLE_POSTAJE = "postaje";

    public static final String COL_ID = BaseColumns._ID;

    public static final String COL_NAME = "name";

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "postajalisca.db";

    public LPPDatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_POSTAJE + " ("

                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + COL_NAME + " TEXT NOT NULL"

                + ");");

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTAJE + ";");

        onCreate(db);

    }

    public long insert(String tableName, ContentValues values) throws NotValidException {

        return getWritableDatabase().insert(tableName, null, values);

    }

    public int update(String tableName, long id, ContentValues values) throws NotValidException {

        String selection = COL_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        return getWritableDatabase().update(tableName, values, selection, selectionArgs);

    }

    public int delete(String tableName, long id) {

        String selection = COL_ID + " = ?";

        String[] selectionArgs = {String.valueOf(id)};

        return getWritableDatabase().delete(tableName, selection, selectionArgs);

    }

    protected void validate(ContentValues values) throws NotValidException {

        if (!values.containsKey(COL_NAME) || values.getAsString(COL_NAME) == null || values.getAsString(COL_NAME).isEmpty()) {

            throw new NotValidException("Station name must be set");

        }

    }
    public Cursor query(String tableName, String orderedBy) {

        String[] projection = {COL_ID, COL_NAME};

        return getReadableDatabase().query(tableName, projection, null, null, null, null, COL_NAME);

    }
    public static class NotValidException extends Throwable {

        public NotValidException(String msg) {

            super(msg);

        }

    }
}