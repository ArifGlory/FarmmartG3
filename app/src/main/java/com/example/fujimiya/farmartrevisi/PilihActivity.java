package com.example.fujimiya.farmartrevisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PilihActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih);
    }

    public void Costomer(View view) {
        finish();
        Intent i = new Intent(PilihActivity.this,SignUpCustomerActivity.class);
        startActivity(i);
        System.exit(0);
    }

    public void User(View view) {
        finish();
        Intent i = new Intent(PilihActivity.this,SignUpUserActivity.class);
        startActivity(i);
        System.exit(0);
    }
}
