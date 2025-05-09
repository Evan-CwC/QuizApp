package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText etNumber1, etNumber2;
    Button btnAdd, btnSubtract;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etNumber1 = findViewById(R.id.et_number1);
        etNumber2 = findViewById(R.id.et_number2);
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        tvResult = findViewById(R.id.tv_result);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
    }

    private void calculate(char op) {
        String num1Str = etNumber1.getText().toString().trim();
        String num2Str = etNumber2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = (op == '+') ? num1 + num2 : num1 - num2;
            tvResult.setText("Result: " + result);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
    }
}
