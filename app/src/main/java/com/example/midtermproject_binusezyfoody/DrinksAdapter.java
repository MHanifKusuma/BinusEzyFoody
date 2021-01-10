package com.example.midtermproject_binusezyfoody;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class DrinksAdapter extends ArrayAdapter<Drinks> {
    public DrinksAdapter(Context context, Vector<Drinks> menuList) {
        super(context, 0, menuList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_drinks, parent, false);
        }

        Drinks drinks = getItem(position);

        ImageView drinksImage = (ImageView) convertView.findViewById(R.id.imageView);
        TextView drinksName = (TextView) convertView.findViewById(R.id.drinksName);
        TextView drinksPrice = (TextView) convertView.findViewById(R.id.drinksPrice);
//        TextView drinksStock = (TextView) convertView.findViewById(R.id.stockTxt);

        drinksImage.setImageResource(drinks.getImageResourceId());
        drinksName.setText(drinks.getName());
        String price = "Rp" + drinks.getPrice();
        drinksPrice.setText(price);
//        drinksStock.setText(drinks.getStock());

        return convertView;
    }
}
