package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kamil on 2018-03-13.
 */

public class ExpenseDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;



    public ExpenseDbHelper(Context context) {
        super(context, DATABASE_NAME,null ,DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_PRODUCT_TABLE =  "CREATE TABLE " + ExpenseContract.ProductEntry.TABLE_NAME + " ("
                + ExpenseContract.ProductEntry._ID + " INTEGER PRIMARY KEY, "
                + ExpenseContract.ProductEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + ExpenseContract.ProductEntry.COLUMN_PRODUCT_CATEGORY + " TEXT NOT NULL, "
                + ExpenseContract.ProductEntry.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL DEFAULT 0);";


        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
