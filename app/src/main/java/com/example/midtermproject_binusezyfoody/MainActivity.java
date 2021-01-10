package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Integer drinkCatId = 0;
    private Integer foodsCatId = 0;
    private Integer snacksCatId = 0;
    private Integer topUp = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView drinksCat = (TextView) findViewById(R.id.drinksTxt);
        TextView foodsCat = (TextView) findViewById(R.id.foodsTxt);
        TextView snacksCat = (TextView) findViewById(R.id.snacksTxt);

        TextView topUpView = (TextView) findViewById(R.id.EzyPayTxt);

        topUpView.setText("EzyPay: Rp " + topUp);


        EzyFoodDatabaseHelper dbHelper = new EzyFoodDatabaseHelper(this);

        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.query("MENU_CATEGORIES", new String[] {"_id","CATEGORY_NAME"},
                    null, null, null, null, null);

            while(cursor.moveToNext()) {
                String nameTxt = cursor.getString(1);

                if(nameTxt.equals("Drinks")) {
                    drinkCatId = Integer.parseInt(cursor.getString(0));
                    drinksCat.setText(nameTxt);
                }
                else if (nameTxt.equals("Foods")) {
                    foodsCat.setText(nameTxt);
                    foodsCatId = Integer.parseInt(cursor.getString(0));
                }
                else if(nameTxt.equals("Snacks")) {
                    snacksCat.setText(nameTxt);
                    snacksCatId = Integer.parseInt(cursor.getString(0));
                }

            }

            cursor.close();
            db.close();
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Database Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void onClickDrinks(View view) {
        Intent moveDrinks = new Intent(this, StoreActivity.class);
        moveDrinks.putExtra(StoreActivity.EXTRA_CATEGORY_ID, drinkCatId );
        startActivity(moveDrinks);
    }
    public void onClickFoods(View view) {
        Intent moveFoods = new Intent(this, StoreActivity.class);
        moveFoods.putExtra(StoreActivity.EXTRA_CATEGORY_ID, foodsCatId );
        startActivity(moveFoods);

    }

    public void onClickSnacks(View view) {
        Intent moveSnacks = new Intent(this, StoreActivity.class);
        moveSnacks.putExtra(StoreActivity.EXTRA_CATEGORY_ID, snacksCatId);
        startActivity(moveSnacks);
    }

    public void onClickMyOrder(View view) {
        Intent moveMyOrder = new Intent(this, MyOrderActivity.class);
        startActivity(moveMyOrder);
    }

    public void onClickTopUp(View view) {
        Intent moveTopTup = new Intent(this, TopUpActivity.class);
        startActivity(moveTopTup);
    }
}