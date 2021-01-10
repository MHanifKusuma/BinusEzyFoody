package com.example.midtermproject_binusezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
    }

    public void onClickTopUp (View view) {
        Intent moveTopUp = new Intent(this, MainActivity.class);

        startActivity(moveTopUp);
    }
}