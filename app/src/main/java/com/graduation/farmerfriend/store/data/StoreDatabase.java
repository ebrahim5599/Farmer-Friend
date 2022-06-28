package com.graduation.farmerfriend.store.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.graduation.farmerfriend.store.pojo.StoreItems;
import java.util.ArrayList;

public class StoreDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "StoreDatabase";
    public static final int DB_VERSION = 1;

    public static final String DB_ITEM_TABLE = "store_items";
    public static final String DB_ID_COLUMN = "id";
    public static final String DB_NAME_COLUMN = "name";
    public static final String DB_DETAILS_COLUMN = "details";
    public static final String DB_AMOUNT_COLUMN = "amount";
    public static final String DB_IMAGE_COLUMN = "image";

    public StoreDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Called when database created for first time.
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_ITEM_TABLE+" ("+DB_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                DB_NAME_COLUMN+" TEXT NOT NULL, "+DB_DETAILS_COLUMN+" TEXT, "+DB_AMOUNT_COLUMN+" INTEGER NOT NULL, "+DB_IMAGE_COLUMN+" BLOB)"); // Without Image.
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Called when database updated.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_ITEM_TABLE); // dangerous if your app is published.
        onCreate(sqLiteDatabase);
    }

    // Insert
    public boolean insertNewItem(StoreItems storeItems){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_NAME_COLUMN, storeItems.getItemName());
        values.put(DB_DETAILS_COLUMN, storeItems.getItemDetails());
        values.put(DB_AMOUNT_COLUMN, storeItems.getNumberOfItems());
        values.put(DB_IMAGE_COLUMN, storeItems.getImageResource());

        long result = sqLiteDatabase.insert(DB_ITEM_TABLE, null, values);
        return result != -1;
    }

    // Update
    public boolean updateItemAmount(StoreItems storeItems, int newValue){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_AMOUNT_COLUMN, newValue);

        String[] args = {""+storeItems.getItemID()};
        int result = sqLiteDatabase.update(DB_ITEM_TABLE, values, DB_ID_COLUMN+"=?", args);
        return result > 0;
    }

    // Delete
    public boolean deleteItem(String itemId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String[] args = {itemId};
        int result = sqLiteDatabase.delete(DB_ITEM_TABLE, DB_ID_COLUMN+"=?", args);
        return result > 0;
    }

    public long getItemsAmount(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(sqLiteDatabase, DB_ITEM_TABLE);
    }

    public ArrayList<StoreItems> getAllItems(){
        ArrayList<StoreItems> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ DB_ITEM_TABLE, null);

        int idColumnIndex = cursor.getColumnIndex(DB_ID_COLUMN);
        int nameColumnIndex = cursor.getColumnIndex(DB_NAME_COLUMN);
        int detailsColumnIndex = cursor.getColumnIndex(DB_DETAILS_COLUMN);
        int amountColumnIndex = cursor.getColumnIndex(DB_AMOUNT_COLUMN);
        int imageColumnIndex = cursor.getColumnIndex(DB_IMAGE_COLUMN);

        // لفحص الـ cursor هل يحتوي على بيانات أم لا
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String details = cursor.getString(detailsColumnIndex);
                int amount = cursor.getInt(amountColumnIndex);
                byte[] image = cursor.getBlob(imageColumnIndex);

                items.add(new StoreItems(id, name, details, amount, image));

            } while (cursor.moveToNext());
            cursor.close();
        }
        return items;
    }

    /* public ArrayList<StoreItems> getAllItems(){
        ArrayList<StoreItems> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ DB_ITEM_TABLE, null);

        int idColumnIndex = cursor.getColumnIndex(DB_ID_COLUMN);
        int nameColumnIndex = cursor.getColumnIndex(DB_NAME_COLUMN);
        int detailsColumnIndex = cursor.getColumnIndex(DB_DETAILS_COLUMN);
        int amountColumnIndex = cursor.getColumnIndex(DB_AMOUNT_COLUMN);

        // لفحص الـ cursor هل يحتوي على بيانات أم لا
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String details = cursor.getString(detailsColumnIndex);
                int amount = cursor.getInt(amountColumnIndex);

                items.add(new StoreItems(id, name, details, amount));

            } while (cursor.moveToNext());
            cursor.close();
        }
        return items;
    } */

    // Search
    public ArrayList<StoreItems> getAnItem(String nameSearch){
        ArrayList<StoreItems> items = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] args = {"%"+nameSearch+"%"};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ DB_ITEM_TABLE + " WHERE "+DB_NAME_COLUMN+ " LIKE ?", args);

        int idColumnIndex = cursor.getColumnIndex(DB_ID_COLUMN);
        int nameColumnIndex = cursor.getColumnIndex(DB_NAME_COLUMN);
        int detailsColumnIndex = cursor.getColumnIndex(DB_DETAILS_COLUMN);
        int amountColumnIndex = cursor.getColumnIndex(DB_AMOUNT_COLUMN);
        int imageColumnIndex = cursor.getColumnIndex(DB_IMAGE_COLUMN);

        // لفحص الـ cursor هل يحتوي على بيانات أم لا
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(idColumnIndex);
                String name = cursor.getString(nameColumnIndex);
                String details = cursor.getString(detailsColumnIndex);
                int amount = cursor.getInt(amountColumnIndex);
                byte[] image = cursor.getBlob(imageColumnIndex);

                items.add(new StoreItems(id, name, details, amount, image));

            } while (cursor.moveToNext());
            cursor.close();
        }
        return items;
    }
}
