package com.tiderdev.dilo.forms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tiderdev.dilo.forms.model.Person;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView TVUserName, TVEmail, TVAddress, TVCity, TVPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.detail));

        TVUserName = (TextView) findViewById(R.id.tv_username);
        TVEmail = (TextView) findViewById(R.id.tv_email);
        TVAddress = (TextView) findViewById(R.id.tv_address);
        TVCity = (TextView) findViewById(R.id.tv_city);
        TVPostalCode = (TextView) findViewById(R.id.tv_postal_code);

        TVUserName.setText(getIntent().getStringExtra("firstName") + " " + getIntent().getStringExtra("lastName"));
        TVEmail.setText(getIntent().getStringExtra("email"));
        TVAddress.setText(getIntent().getStringExtra("address"));
        TVCity.setText(getIntent().getStringExtra("city"));
        TVPostalCode.setText(getIntent().getStringExtra("postalCode"));
    }
}
