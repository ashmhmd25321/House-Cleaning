package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    TextView userNAme;
    Button giveFeed, viewFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        userNAme = findViewById(R.id.textView53);
        giveFeed = findViewById(R.id.button20);
        viewFeed = findViewById(R.id.button21);

        giveFeed.setBackgroundColor(Color.BLUE);
        viewFeed.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("user");
        userNAme.setText(name);
        String user = userNAme.getText().toString();

        giveFeed.setOnClickListener(v -> {
            Intent intent = new Intent(this, GiveFeedbackActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });

        viewFeed.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewFeedActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}