package com.example.midtermproject_binusezyfoody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyOrderAdapter extends ArrayAdapter<MyOrder> implements ButtonClickListener{
    private ButtonClickListener deleteItemListener = null;

    public MyOrderAdapter(Context context, MyOrder[] orderList, ButtonClickListener listener) {
        super(context, 0, orderList);
        deleteItemListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_my_order, parent, false);
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

        Button deleteItemButton = (Button) convertView.findViewById(R.id.deleteBtn);
        deleteItemButton.setTag(position);
        deleteItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteItemListener != null) {
                    deleteItemListener.onButtonClickListener((Integer) view.getTag());
                }
            }
        });

        return convertView;
    }

    @Override
    public void onButtonClickListener(int position) {

    }
}
