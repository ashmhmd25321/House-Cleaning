package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHouseInfo extends AppCompatActivity {

    TextView user, noOfRooms, noOfBathrooms, floorType, address, houseId;
    Button backBtn, createOrUpdate;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_house_info);

        DatabaseHelper dbh = new DatabaseHelper(this);

        user = findViewById(R.id.textView19);
        backBtn = findViewById(R.id.button16);
        createOrUpdate = findViewById(R.id.button17);
        noOfRooms = findViewById(R.id.textView29);
        noOfBathrooms = findViewById(R.id.textView30);
        floorType = findViewById(R.id.textView31);
        address = findViewById(R.id.textView32);
        houseId = findViewById(R.id.textView33);
        imageView = findViewById(R.id.imageView6);

        backBtn.setBackgroundColor(Color.BLUE);
        createOrUpdate.setBackgroundColor(Color.BLUE);

        String name = getIntent().getStringExtra("name");
        user.setText(name);

        String uN = user.getText().toString();

        Cursor cursor = dbh.viewHouse(uN);
        cursor.moveToNext();
        noOfRooms.setText(cursor.getString(2));
        noOfBathrooms.setText(cursor.getString(3));
        floorType.setText(cursor.getString(4));
        address.setText(cursor.getString(5));
        houseId.setText(cursor.getString(0));
        byte[] image = cursor.getBlob(6);
        Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bmp);

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user", uN);
            startActivity(intent);
        });

        createOrUpdate.setOnClickListener(v -> {
            Intent intent= new Intent(this, HouseInfoActivity.class);
            intent.putExtra("name", uN);
            startActivity(intent);
        });
    }
}