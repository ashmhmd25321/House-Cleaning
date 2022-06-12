package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewUserPostActivity extends AppCompatActivity {

    ListView listView;
    TextView userName;
    Button backBtn;
    ArrayList<Post> arrayList;
    PostAdapter adapter;
    DatabaseHelper dbh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_post);


        backBtn = findViewById(R.id.button19);
        listView = findViewById(R.id.listView);
        userName = findViewById(R.id.textView49);

        backBtn.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("user");
        userName.setText(name);
        String user = userName.getText().toString();

        showPostData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewUserPostActivity.this, PostDetailsActivity.class);
                intent.putExtra("POSITION", String.valueOf(position));
                intent.putExtra("name", user);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }

    private void showPostData() {
        arrayList = dbh.getPost();
        adapter = new PostAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}