package uk.co.ribot.Knacket.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import uk.co.ribot.Knacket.data.model.Ad;

public class Db {
    public Db() {}

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

        public static ContentValues toContentValues(Ad ad) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, ad.getName());
            values.put(COLUMN_CATEGORY, ad.getCategory());
            values.put(COLUMN_DATE, ad.getDate());
            values.put(COLUMN_DESC, ad.getDescription());
            values.put(COLUMN_PRICE, ad.getPrice());
            values.put(COLUMN_RATING, ad.getRating());
            return values;
        }

        public static Ad parseCursor(Cursor cursor) {
            Ad ad = new Ad();
            ad.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
            ad.setCategory(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY)));
            ad.setDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
            ad.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESC)));
            ad.setPrice(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
            ad.setRating(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_RATING)));
            return ad;
        }
    }
}