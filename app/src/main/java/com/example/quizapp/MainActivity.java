package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.et_name);
        buttonStart = findViewById(R.id.btn_start);

        String passedName = getIntent().getStringExtra("username");
        if (passedName != null) {
            editTextName.setText(passedName);
        }

        buttonStart.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("username", name);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCalculator = findViewById(R.id.btn_calculator);
        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

    }
}
