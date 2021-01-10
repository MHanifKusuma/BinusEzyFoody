package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class OrderComplete extends AppCompatActivity {
    MyOrder myOrders[] = MyOrder.orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);

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

    private void populateListView() {
        OrderCompleteAdapter orderCompleteAdapter = new OrderCompleteAdapter(this, myOrders);
        ListView orderList = (ListView) findViewById(R.id.myOrderList);
        orderList.setAdapter(orderCompleteAdapter);
    }

    public void onClickHome (View view) {
        Intent moveHome = new Intent(this, MainActivity.class);
        startActivity(moveHome);
    }
}