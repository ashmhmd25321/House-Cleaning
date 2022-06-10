package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewHouseInfo extends AppCompatActivity {

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house_info);

        user = findViewById(R.id.textView19);

        String name = getIntent().getStringExtra("name");
        user.setText(name);
    }
}