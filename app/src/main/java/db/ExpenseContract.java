package db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Kamil on 2018-03-13.
 */

public class ExpenseContract {


    public ExpenseContract() {

    }

    public static final String CONTENT_AUTHORITY = "com.example.android.expenselist.db";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PRODUCTS = "products";

    public static abstract class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        public final static String TABLE_NAME = "products";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME = "name";

        public final static String COLUMN_PRODUCT_CATEGORY = "category";

        public final static String COLUMN_PRODUCT_PRICE = "price";


    }
}
