package com.fj.naufalprakoso.newpopcorntime.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.fj.naufalprakoso.newpopcorntime.entity.Favorite;

import java.util.ArrayList;

import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.BANNER;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.POPULARITY;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.fj.naufalprakoso.newpopcorntime.db.DatabaseContract.TABLE_MOVIE;

/**
 * Created by NaufalPrakoso on 19/03/18.
 */

public class FavoriteHelper {

    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FavoriteHelper(Context context){
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Favorite> query(){
        ArrayList<Favorite> arrayList = new ArrayList<Favorite>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null, BaseColumns._ID +" DESC",null);
        cursor.moveToFirst();
        Favorite note;
        if (cursor.getCount()>0) {
            do {
                note = new Favorite();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                note.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                note.setPopulariry(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                note.setBanner(cursor.getString(cursor.getColumnIndexOrThrow(BANNER)));
                note.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                arrayList.add(note);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Favorite note){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(TITLE, note.getTitle());
        initialValues.put(DESCRIPTION, note.getOverview());
        initialValues.put(RELEASE_DATE, note.getRelease_date());
        initialValues.put(POPULARITY, note.getPopulariry());
        initialValues.put(BANNER, note.getBanner());
        initialValues.put(POSTER, note.getPoster());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int delete(int id){
        return database.delete(TABLE_MOVIE, BaseColumns._ID + " = '"+id+"'", null);
    }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                , BaseColumns._ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }

    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                , BaseColumns._ID + " DESC");
    }

    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE, BaseColumns._ID + " = ?", new String[]{id});
    }
}
