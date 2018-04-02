package info.erwandy.dicodingmynotesapp.db;

import android.database.Cursor;
import android.net.Uri;

import static info.erwandy.dicodingmynotesapp.db.NoteColumns.TABLE_NOTE;

/**
 * Created by Nursing Bank IT Dept on 3/22/2018.
 */

public class DatabaseContract {

    //Ditambahkan untuk Content Provider
    public static final String AUTHORITY = "info.erwandy.dicodingmynotesapp";

    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NOTE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }
}
