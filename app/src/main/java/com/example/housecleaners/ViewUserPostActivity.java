package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewUserPostActivity extends AppCompatActivity {

    TextView userName;
    ListView listView;
    Button backBtn;
    ArrayList<Post> arrayList;
    PostAdapter adapter;
    DatabaseHelper dbh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_post);

        userName = findViewById(R.id.textView38);
        backBtn = findViewById(R.id.button19);
        listView = findViewById(R.id.listView);

        backBtn.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("name");
        userName.setText(name);
        String uN = userName.getText().toString();

        showPostData();



        backBtn.setOnClickListener(v -> {
            finish();
        });
    }

    private void showPostData() {
        arrayList = dbh.getPost();
        adapter = new PostAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}