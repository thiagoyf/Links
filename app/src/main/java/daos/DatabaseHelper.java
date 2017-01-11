package daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thiagoyf on 1/11/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nome do DataBase
    private static final String DATABASE_NAME = "Links";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String LINK_TABLE = "link";

    // Table Columns
    public static final String LINK_ID = "id";
    public static final String LINK_TITLE = "title";
    public static final String LINK_CATEGORY = "category";
    public static final String LINK_URL = "url";
    public static final String LINK_STATUS = "status";

    // CREATE TABLE
    public static final String CREATE_LINK_TABLE = "CREATE TABLE IF NOT EXISTS "
            + LINK_TABLE + "("
            + LINK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + LINK_TITLE + " TEXT UNIQUE, "
            + LINK_CATEGORY + " TEXT, "
            + LINK_URL + " TEXT, "
            + LINK_STATUS + " INTEGER)";

    // DELETE DATABASE
    public static final String DROP_LINK_TABLE = "DROP TABLE IF EXISTS " + LINK_TABLE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
        sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LINK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_LINK_TABLE);
        onCreate(sqLiteDatabase);
    }
}
