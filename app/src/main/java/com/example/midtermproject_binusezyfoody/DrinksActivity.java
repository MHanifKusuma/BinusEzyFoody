package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.Vector;

public class DrinksActivity extends AppCompatActivity {
    Vector<Drinks> menuList;

    private Integer resId = 0, catId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        Intent receivedIntent = getIntent();
        Bundle receivedBundle = receivedIntent.getBundleExtra("passedBundle");

        catId = receivedBundle.getInt("catId", 0);
        resId = receivedBundle.getInt("resId", 0);

        populateListView();
    }

    private void populateListView() {
        menuList = new Vector<>();

        SQLiteOpenHelper EzyHelper = new EzyFoodDatabaseHelper(this);

        try {
            String name = "";
            int price = 0, imageId = 0, stock = 0;

            SQLiteDatabase db = EzyHelper.getReadableDatabase();

            Cursor menus = db.query("MENUS", new String[] {"_id", "CATEGORY_ID", "MENU_NAME", "MENU_PRICE", "MENU_IMAGE_RESOURCE_ID"},
                    "CATEGORY_ID = ?", new String[] {catId.toString()}, null, null, null);

            while (menus.moveToNext()) {
                name = menus.getString(2);
                price = Integer.parseInt(menus.getString(3));
                imageId = Integer.parseInt(menus.getString(4));

//                Cursor stocks = db.query("DETAIL_RESTAURANTS", new String[] {"_id", "RESTAURANT_ID", "MENU_ID", "MENU_STOCK"},
//                        "RESTAURANT_ID = ? AND MENU_ID = ?", new String[] {resId.toString(), menus.getString(0).toString()},
//                        null, null, null);

//                stock = Integer.parseInt(stocks.getString(3));

                Drinks newMenu = new Drinks();

                newMenu.setName(name);
                newMenu.setPrice(price);
                newMenu.setImageResourceId(imageId);
//                newMenu.setStock(stock);

                menuList.add(newMenu);
            }

        } catch (SQLException e) {

        }

        DrinksAdapter drinksAdapter = new DrinksAdapter(this, menuList);
        ListView drinksList = (ListView) findViewById(R.id.drinksList);
        drinksList.setAdapter(drinksAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {
                Intent moveOrder = new Intent(DrinksActivity.this, OrderActivity.class);
                moveOrder.putExtra(OrderActivity.EXTRA_DRINKID, (int) id);
                startActivity(moveOrder);
            }
        };

        drinksList.setOnItemClickListener(itemClickListener);
    }

    public void onClickMyOrder(View view) {
        Intent moveMyOrder = new Intent(this, MyOrderActivity.class);
        startActivity(moveMyOrder);
    }

    public void onClickHome (View view) {
        Intent moveHome = new Intent(this, MainActivity.class);
        startActivity(moveHome);
    }
}