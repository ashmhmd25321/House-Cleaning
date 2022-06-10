package com.example.housecleaners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class HouseInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView uN, floorType;
    EditText address, noOfRooms, noOfBathrooms, houseId;
    Spinner spinner;
    Button back, browse, add, viewHouseInfo;
    ImageView imageView;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);

        DatabaseHelper dbh = new DatabaseHelper(this);

        uN = findViewById(R.id.textView17);
        floorType = findViewById(R.id.textView23);
        spinner = findViewById(R.id.spinner3);
        back = findViewById(R.id.button13);
        browse = findViewById(R.id.button12);
        imageView = findViewById(R.id.imageView3);
        add = findViewById(R.id.button14);
        address = findViewById(R.id.editTextTextPersonName6);
        noOfRooms = findViewById(R.id.editTextNumber);
        noOfBathrooms = findViewById(R.id.editTextNumber2);
        houseId = findViewById(R.id.editTextNumber4);
        viewHouseInfo = findViewById(R.id.button15);

        back.setBackgroundColor(Color.BLUE);
        browse.setBackgroundColor(Color.BLUE);
        add.setBackgroundColor(Color.BLUE);
        viewHouseInfo.setBackgroundColor(Color.BLUE);


        String name = getIntent().getStringExtra("name");
        uN.setText(name);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.floors, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        back.setOnClickListener(v -> {
            finish();
        });

        //image chooser
        browse.setOnClickListener(v -> {
            openGallery();
        });

        add.setOnClickListener(v -> {
            String id = houseId.getText().toString();
            String user = uN.getText().toString();
            String rooms = noOfRooms.getText().toString();
            String bathRooms = noOfBathrooms.getText().toString();
            String floor = floorType.getText().toString();
            String adrs = address.getText().toString();
            byte[] img = imageViewToByte(imageView);

            if (id.equals("") || user.equals("") || adrs.equals("") || rooms.equals("")) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkHouse = dbh.checkHouse(user);
                if (checkHouse == false) {
                    Boolean insert = dbh.insertHouseInfo(id, user, rooms, bathRooms, floor, adrs, img);
                    if (insert == true) {
                        Toast.makeText(this, "House Registered Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Registered Failed", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "User's House already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHouseInfo.setOnClickListener(v -> {
            String user = uN.getText().toString();
            Intent intent = new Intent(this, ViewHouseInfo.class);
            intent.putExtra("name", user);
            startActivity(intent);
        });

    }

    private byte[] imageViewToByte(@NonNull ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        floorType.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}