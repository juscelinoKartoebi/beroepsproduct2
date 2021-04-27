package sr.unasat.beroepsproduct2.Database;

import android.net.Uri;
import android.provider.BaseColumns;

public class OrderContract {

    public OrderContract() {
    }
//dit is de locatie waarin die tabellen worden opgeslagen.
    public static final String CONTENT_AUTHORITY = "sr.unasat.test.data.OrderProvider";
    public static final Uri BASE_URI = Uri.parse("content://" + CONTENT_AUTHORITY); // creert een new uri object van een formated string.

    public static final String PATH = "ordering";

    public static abstract class OrderEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URI, PATH);

        public static final String TABLE_NAME = "ordering";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";

    }

}