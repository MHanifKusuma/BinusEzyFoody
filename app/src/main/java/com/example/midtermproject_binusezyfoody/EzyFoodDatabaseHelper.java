package com.example.midtermproject_binusezyfoody;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EzyFoodDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ezyfoody";
    private static final int DB_VERSION = 1;

    EzyFoodDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private static void insertCategory(SQLiteDatabase db, String name) {
        ContentValues categoryValues = new ContentValues();
        categoryValues.put("CATEGORY_NAME", name);
        db.insert("MENU_CATEGORIES", null, categoryValues);
    }

    private static void insertMenu(SQLiteDatabase db, String name, Integer price, Integer resourceId, Integer category) {
        ContentValues menuValues = new ContentValues();

        menuValues.put("CATEGORY_ID", category);
        menuValues.put("MENU_NAME", name);
        menuValues.put("MENU_PRICE", price);
        menuValues.put("MENU_IMAGE_RESOURCE_ID", resourceId);

        db.insert("MENUS", null, menuValues);
    }

    private static void insertRestaurant(SQLiteDatabase db, String name, String lat, String lng) {
        ContentValues restoValues = new ContentValues();

        restoValues.put("RESTAURANT_NAME", name);
        restoValues.put("RESTAURANT_LATITUDE", lat);
        restoValues.put("RESTAURANT_LONGITUDE", lng);

        db.insert("RESTAURANTS", null, restoValues);
    }

    private static void insertRestoDetails(SQLiteDatabase db, Integer restoId, Integer menuId, Integer stock) {
        ContentValues restoDetailsValues = new ContentValues();

        restoDetailsValues.put("RESTAURANT_ID", restoId);
        restoDetailsValues.put("MENU_ID", menuId);
        restoDetailsValues.put("MENU_STOCK", stock);

        db.insert("DETAIL_RESTAURANTS", null, restoDetailsValues);
    }

    private void makeCategoryTable(SQLiteDatabase db) {
        String queryMenuCategory = ("CREATE TABLE MENU_CATEGORIES ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY_NAME TEXT); ");
        db.execSQL(queryMenuCategory);

    }

    private void makeMenuTable(SQLiteDatabase db) {
        String queryMenu = ("CREATE TABLE MENUS ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "CATEGORY_ID INTEGER, " +
                "MENU_NAME TEXT, " +
                "MENU_PRICE INTEGER, " +
                "MENU_IMAGE_RESOURCE_ID, " +
                "FOREIGN KEY(CATEGORY_ID) REFERENCES MENU_CATEGORIES(_id) ON UPDATE CASCADE); ");

        db.execSQL(queryMenu);
    }

    private void makeHeaderTransactionTable(SQLiteDatabase db) {
        String queryHeaderTrans = ("CREATE TABLE HEADER_TRANSACTIONS ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "RESTAURANT_ID INTETGER, " +
                "TRANSACTION_DATE NUMERIC, " +
                "TOTAL_PRICE INTEGER, " +
                "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANTS(_id) ON UPDATE CASCADE); ");

        db.execSQL(queryHeaderTrans);
    }

    private void makeDetailTransactionTable(SQLiteDatabase db) {
        String queryDetailTrans = ("CREATE TABLE DETAIL_TRANSACTION ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TRANSACTION_ID INTEGER, " +
                "MENU_ID INTEGER, " +
                "QUANTITY INTEGER, " +
                "FOREIGN KEY(TRANSACTION_ID) REFERENCES HEADER_TRANSACTIONS(_id) ON UPDATE CASCADE, " +
                "FOREIGN KEY(MENU_ID) REFERENCES MENUS(_id) ON UPDATE CASCADE); ");

        db.execSQL(queryDetailTrans);
    }

    private void makeCartsTable(SQLiteDatabase db) {
        String queryCarts = ("CREATE TABLE CARTS ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "RESTAURANT_ID INTEGER, " +
                "MENU_ID INTEGER, " +
                "QUANTITY INTEGER, " +
                "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANTS(_id) ON UPDATE CASCADE);" +
                "FOREIGN KEY(MENU_ID) REFERENCES MENU(_id) ON UPDATE CASCADE); ");

        db.execSQL(queryCarts);
    }

    private void makeRestaurantTable(SQLiteDatabase db) {
        String queryResto = ("CREATE TABLE RESTAURANTS ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "RESTAURANT_NAME TEXT, " +
                "RESTAURANT_LATITUDE TEXT, " +
                "RESTAURANT_LONGITUDE TEXT); ");

        db.execSQL(queryResto);
    }

    private void makeDetailRestaurants(SQLiteDatabase db) {
        String queryRestoDetail = ("CREATE TABLE DETAIL_RESTAURANTS ( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "RESTAURANT_ID INTEGER, " +
                "MENU_ID INTEGER, " +
                "MENU_STOCK INTEGER, " +
                "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANTS(_id) ON UPDATE CASCADE, " +
                "FOREIGN KEY(MENU_ID) REFERENCES MENUS(_id) ON UPDATE CASCADE); ");

        db.execSQL(queryRestoDetail);
    }

    private void populateRestaurantTable(SQLiteDatabase db) {
        insertRestaurant(db, "EzyFoody Alsut", "-6.2232004023710585", "106.6488380704966");
        insertRestaurant(db, "EzyFoody Kemanggisan", "-6.200626786640842", "106.78510782104438");
    }

    private void populateCategoryTable(SQLiteDatabase db) {
        insertCategory(db, "Drinks");
        insertCategory(db, "Snacks");
        insertCategory(db, "Foods");
    }

    private void populateMenuTable(SQLiteDatabase db) {
        //drinks menu
        insertMenu(db, "Apple Juice", 10000, R.drawable.apple_icon, 1);
        insertMenu(db, "Avocado Juice", 15000, R.drawable.avocado_icon, 1);
        insertMenu(db, "Mango Juice", 13000, R.drawable.manggo_icon, 1);
        insertMenu(db, "Mineral Water", 6000, R.drawable.mineral_icon, 1);

        //foods menu
        insertMenu(db, "Fried Rice", 30000, R.drawable.friedrice_icon, 3);
        insertMenu(db, "Taichan Satay", 25000, R.drawable.taichan_icon, 3);
        insertMenu(db, "Fried Kwetiau", 40000, R.drawable.kwetiau_icon, 3);
        insertMenu(db, "Chicken Cordon Bleu", 60000, R.drawable.cordon_icon, 3);

        //snacks menu
        insertMenu(db, "Salty Popcorn", 20000, R.drawable.popcorn_icon, 2);
        insertMenu(db, "Caramel Popcorn", 25000, R.drawable.caramel_icon, 2);
        insertMenu(db, "Nachos", 18000, R.drawable.nachos_icon, 2);
        insertMenu(db, "French Fries", 15000, R.drawable.fries_icon, 2);
    }

    private void populateRestoDetailTable(SQLiteDatabase db) {
        //drinks stock for EzyFood Alsut
        insertRestoDetails(db, 1, 1, 10);
        insertRestoDetails(db, 1, 2, 5);
        insertRestoDetails(db, 1, 3, 3);
        insertRestoDetails(db, 1, 4, 30);

        //drinks stock for EzyFod Kemanggisan
        insertRestoDetails(db, 2, 1, 2);
        insertRestoDetails(db, 2, 2, 4);
        insertRestoDetails(db, 2, 3, 6);
        insertRestoDetails(db, 2, 4, 13);

        //foods stock for EzyFood Alsut
        insertRestoDetails(db, 1, 5, 50);
        insertRestoDetails(db, 1, 6, 30);
        insertRestoDetails(db, 1, 7, 20);
        insertRestoDetails(db, 1, 8, 10);

        //foods stock for EzyFood Kemanggisan
        insertRestoDetails(db, 2, 5, 10);
        insertRestoDetails(db, 2, 6, 20);
        insertRestoDetails(db, 2, 7, 30);
        insertRestoDetails(db, 2, 8, 50);

        //snacks stock for EzyFood Alsut
        insertRestoDetails(db, 1, 9, 9);
        insertRestoDetails(db, 1, 10, 8);
        insertRestoDetails(db, 1, 11, 7);
        insertRestoDetails(db, 1, 12, 6);

        //snacks stock for EzyFood Kemanggisan
        insertRestoDetails(db, 2, 9, 11);
        insertRestoDetails(db, 2, 10, 12);
        insertRestoDetails(db, 2, 11, 16);
        insertRestoDetails(db, 2, 12, 19);
    }

    private void databaseSeeder(SQLiteDatabase db) {
        makeCategoryTable(db);
        populateCategoryTable(db);

        makeMenuTable(db);
        populateMenuTable(db);

        makeRestaurantTable(db);
        populateRestaurantTable(db);

        makeDetailRestaurants(db);
        populateRestoDetailTable(db);
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion < 1) {
            databaseSeeder(db);

            makeHeaderTransactionTable(db);
            makeDetailTransactionTable(db);
            makeCartsTable(db);
        }
    }
}
