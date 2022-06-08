package com.example.housecleaners;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button back, retrieve;
    Spinner spinner;
    TextView question, password;
    EditText answer, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        DatabaseHelper dbh = new DatabaseHelper(this);

        back = findViewById(R.id.button5);
        spinner = findViewById(R.id.spinner2);
        question = findViewById(R.id.textView13);
        password = findViewById(R.id.textView12);
        answer = findViewById(R.id.editTextTextPersonName5);
        userName = findViewById(R.id.editTextTextPersonName4);
        retrieve = findViewById(R.id.button4);

        retrieve.setBackgroundColor(Color.BLUE);
        back.setBackgroundColor(Color.BLUE);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        back.setOnClickListener(v -> {
            finish();
        });

        retrieve.setOnClickListener(v -> {
            String user = userName.getText().toString();
            String ans = answer.getText().toString();
            String secQue = question.getText().toString();

            if (user.equals("") || ans.equals("")) {
                Toast.makeText(ForgotPasswordActivity.this, "You are missing username/answer field", Toast.LENGTH_SHORT).show();
            } else {
                Cursor cursor = dbh.viewPassword(user, ans, secQue);

                if (cursor.getCount() != 0) {
                    Toast.makeText(ForgotPasswordActivity.this, "Username and Answer are matched", Toast.LENGTH_SHORT).show();

                    cursor.moveToNext();
                    password.setText(cursor.getString(1));
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Invalid Username or Answer or Security Question. Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choice = parent.getItemAtPosition(position).toString();
        question.setText(choice);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}