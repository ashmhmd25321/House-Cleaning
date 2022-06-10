package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView userName, userType;
    Button houseInfo, createPost, customerFeedback, viewPost, cleanerFeedback, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper dbh = new DatabaseHelper(this);

        userName = findViewById(R.id.textView8);
        userType = findViewById(R.id.textView14);
        houseInfo = findViewById(R.id.button6);
        createPost = findViewById(R.id.button7);
        customerFeedback = findViewById(R.id.button8);
        viewPost = findViewById(R.id.button10);
        cleanerFeedback = findViewById(R.id.button11);
        back = findViewById(R.id.button9);

        houseInfo.setBackgroundColor(Color.BLUE);
        createPost.setBackgroundColor(Color.BLUE);
        customerFeedback.setBackgroundColor(Color.BLUE);
        viewPost.setBackgroundColor(Color.BLUE);
        cleanerFeedback.setBackgroundColor(Color.BLUE);
        back.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("user");
        userName.setText(name);

        String user = userName.getText().toString();

        Cursor cursor = dbh.viewUserType(user);
        cursor.moveToNext();
        userType.setText(cursor.getString(4));

        String uTName = userType.getText().toString();
        if (uTName.equals("Customer")) {
            viewPost.setVisibility(View.GONE);
            cleanerFeedback.setVisibility(View.GONE);
        }else if (uTName.equals("Cleaner")) {
            houseInfo.setVisibility(View.GONE);
            createPost.setVisibility(View.GONE);
            customerFeedback.setVisibility(View.GONE);
        }

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        houseInfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, HouseInfoActivity.class);
            intent.putExtra("name", user);
            startActivity(intent);
        });
    }
}