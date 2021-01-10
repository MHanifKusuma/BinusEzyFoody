package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class OrderActivity extends AppCompatActivity {

    public static final String EXTRA_DRINKID = "drinkId";
    public MyOrder myOrder;

    public MyOrder getMyOrder() {
        return myOrder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        loadPage();
    }

    private void loadPage() {
//        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_DRINKID);
//        Drinks drinks = Drinks.drinksList[drinkId];
//
//        ImageView drinkImage = (ImageView) findViewById(R.id.imageView);
//        drinkImage.setImageResource(drinks.getImageResourceId());
//
//        TextView drinkNameTxt = (TextView) findViewById(R.id.drinksName);
//        drinkNameTxt.setText(drinks.getName());
//
//        String drinkPrice = "Rp" + drinks.getPrice();
//        TextView drinkPriceTxt = (TextView) findViewById(R.id.drinksPrice);
//        drinkPriceTxt.setText(drinkPrice);
//
//        addToMyOrder(drinks);
    }

    public void addToMyOrder(Drinks drinks) {
        String drinkQuantity;
        EditText qtyInput = (EditText) findViewById(R.id.topUpInput);
        drinkQuantity = qtyInput.getText().toString();
        //myOrder = new MyOrder(drinks, Integer.parseInt(drinkQuantity));
    }

    public void onClickMyOrder(View view) {
        Intent moveMyOrder = new Intent(this, MyOrderActivity.class);
        startActivity(moveMyOrder);
    }

    public void onClickOrderMore(View view) {
        Intent moveOrder = new Intent(this, DrinksActivity.class);
        startActivity(moveOrder);
    }

    public void onClickHome (View view) {
        Intent moveHome = new Intent(this, MainActivity.class);
        startActivity(moveHome);
    }
}