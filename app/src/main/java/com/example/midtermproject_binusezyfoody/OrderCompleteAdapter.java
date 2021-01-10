package com.example.midtermproject_binusezyfoody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderCompleteAdapter extends ArrayAdapter<MyOrder> {
    public OrderCompleteAdapter(Context context, MyOrder[] myOrders) {
        super(context, 0, myOrders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_order_complete, parent, false);
        }

        MyOrder myOrders = getItem(position);

        ImageView drinksImage = (ImageView) convertView.findViewById(R.id.imageView);
        TextView drinksName = (TextView) convertView.findViewById(R.id.drinksName);
        TextView drinksPrice = (TextView) convertView.findViewById(R.id.drinksPrice);
        TextView drinksQuantity = (TextView) convertView.findViewById(R.id.topUpLbl);

        drinksImage.setImageResource(myOrders.getImageResourceId());
        drinksName.setText(myOrders.getName());
        String price = "Rp" + myOrders.getPrice();
        drinksPrice.setText(price);
        String quantity = myOrders.getQuantity().toString() + " X ";
        drinksQuantity.setText(quantity);

        return convertView;
    }
}
