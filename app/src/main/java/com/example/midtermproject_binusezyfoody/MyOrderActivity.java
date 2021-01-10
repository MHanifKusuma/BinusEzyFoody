package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    MyOrder myOrders[] = MyOrder.orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        String grandTotal = "Total Price: Rp " + calculateGrandTotal();
        TextView grandTotalTxt = (TextView) findViewById(R.id.totalPriceTxt);
        grandTotalTxt.setText(grandTotal);

        populateListView();
    }

    private String calculateGrandTotal() {
        Integer grandTotal = 0;

        MyOrder orderList[] = MyOrder.orderList;

        int items = orderList.length;
        int count = 0;
        int subtotal;

        while(count < items) {
            subtotal = 0;
            subtotal = orderList[count].getPrice() * orderList[count].getQuantity();
            grandTotal = grandTotal + subtotal;

            count++;
        }

        return  grandTotal.toString();
    }

    private void onClickDelete(int position) {
        for(int i=position; i < myOrders.length; i++){
            myOrders[i] = myOrders[i+1];
        }
        myOrders[myOrders.length-1] = null;

        Intent refreshPage = new Intent(this, MyOrderActivity.class);
        startActivity(refreshPage);
    }

    private void populateListView() {
        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(this, myOrders, new ButtonClickListener() {
            @Override
            public void onButtonClickListener(int position) {
                onClickDelete(position);
            }
        });
        ListView orderList = (ListView) findViewById(R.id.myOrderList);
        orderList.setAdapter(myOrderAdapter);
    }

    public void onClickPay(View view) {
        Intent movePayNow = new Intent(this, OrderComplete.class);
        startActivity(movePayNow);
    }

    public void onClickHome (View view) {
        Intent moveHome = new Intent(this, MainActivity.class);
        startActivity(moveHome);
    }
}