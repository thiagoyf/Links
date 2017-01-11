package daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import models.Link;

/**
 * Created by thiagoyf on 1/11/17.
 */

public class LinkDao {
    private static final int SAVE = 1;
    private static final int UPDATE = 2;

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    private String[] projecao = {
            DatabaseHelper.LINK_ID,
            DatabaseHelper.LINK_TITLE,
            DatabaseHelper.LINK_CATEGORY,
            DatabaseHelper.LINK_URL,
            DatabaseHelper.LINK_STATUS
    };

    public LinkDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public SQLiteDatabase getDatabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = databaseHelper.getWritableDatabase();
        }

        return sqLiteDatabase;
    }

    public List<Link> listLink() {
        Cursor cursor = getDatabase().query(DatabaseHelper.LINK_TABLE, projecao,
                DatabaseHelper.LINK_STATUS + "=?", new String[]{"0"}, null, null, null);

        List<Link> links = new ArrayList<Link>();
        while (cursor.moveToNext()) {
            Link link = getLink(cursor);
            links.add(link);
        }

        cursor.close();
        return links;
    }

    public long createLink(Link link) {
        ContentValues valores = buildContentValues(SAVE, link);

        return getDatabase().insert(DatabaseHelper.LINK_TABLE, null, valores);
    }

    public long updateLink(Link link) {
        ContentValues valores = buildContentValues(UPDATE, link);

        return getDatabase().update(DatabaseHelper.LINK_TABLE, valores, DatabaseHelper
                .LINK_ID + " = ?", new String[]{String.valueOf(link.getId())});
    }

    public Link searchLinkById(int id) {
        Cursor cursor = getDatabase().query(DatabaseHelper.LINK_TABLE, projecao, "id = ?",
                new String[]{Integer.toString(id)}, null, null, null);

        Link link = null;
        if (cursor.moveToFirst()) {
            link = getLink(cursor);
        }

        cursor.close();
        return link;
    }

    public Link searchLinkByTitle(String title) {
        Cursor cursor = getDatabase().query(DatabaseHelper.LINK_TABLE, projecao,
                DatabaseHelper.LINK_TITLE + " = ?", new String[]{title}, null, null, null);

        Link link = null;
        if (cursor.moveToFirst()) {
            link = getLink(cursor);
        }

        cursor.close();
        return link;
    }

    public Link searchLinkByTitleAndCategory(String title, String category) {
        Cursor cursor = getDatabase().query(DatabaseHelper.LINK_TABLE, projecao,
                DatabaseHelper.LINK_TITLE + " = ? AND " + DatabaseHelper.LINK_CATEGORY + " = ?",
                new String[]{title, category}, null, null, null);

        Link link = null;
        if (cursor.moveToFirst()) {
            link = getLink(cursor);
        }

        cursor.close();
        return link;
    }

    private ContentValues buildContentValues(int option, Link link) {
        ContentValues valores = new ContentValues();
        valores.put(DatabaseHelper.LINK_TITLE, link.getTitle());
        valores.put(DatabaseHelper.LINK_CATEGORY, link.getCategory());
        valores.put(DatabaseHelper.LINK_URL, link.getUrl());
        valores.put(DatabaseHelper.LINK_STATUS, link.getStatus());

        if (option == UPDATE) {
            valores.put(DatabaseHelper.LINK_ID, link.getId());
        }

        return valores;
    }

    private Link getLink(Cursor cursor) {
        return new Link(
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.LINK_ID)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.LINK_TITLE)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.LINK_CATEGORY)),
                cursor.getString(cursor.getColumnIndex(DatabaseHelper.LINK_URL)),
                cursor.getInt(cursor.getColumnIndex(DatabaseHelper.LINK_STATUS))
        );
    }

    public void close() {
        databaseHelper.close();
        sqLiteDatabase = null;
    }
}
