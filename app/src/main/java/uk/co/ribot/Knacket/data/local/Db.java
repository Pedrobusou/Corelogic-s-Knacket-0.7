package uk.co.ribot.Knacket.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import uk.co.ribot.Knacket.data.model.Buyer;

public class Db {

    public Db() { }

    public abstract static class BuyerTable {  //HAVENT WORKED THIS
        public static final String TABLE_NAME = "buyer";

        public static final String COLUMN_id = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_RATING = "rating";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_id + " TEXT PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_CATEGORY + " TEXT NOT NULL, " +
                        COLUMN_DATE + " TEXT NOT NULL, " +
                        COLUMN_DESC + " INTEGER NOT NULL, " +
                        COLUMN_PRICE + " TEXT NOT NULL, " +
                        COLUMN_RATING + " TEXT" +
                " ); ";

        public static ContentValues toContentValues(Buyer buyer) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, buyer.getName());
            values.put(COLUMN_CATEGORY, buyer.getCategory());
            values.put(COLUMN_DATE, buyer.getDate());
            values.put(COLUMN_DESC, buyer.getDescription());
            values.put(COLUMN_PRICE, buyer.getPrice());
            values.put(COLUMN_RATING, buyer.getRating());
            return values;
        }

        public static Buyer parseCursor(Cursor cursor) {
            Buyer buyer = new Buyer();
            buyer.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            buyer.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)));
            buyer.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            buyer.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESC)));
            buyer.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
            buyer.setRating(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RATING)));
            return buyer;
        }
    }
}
