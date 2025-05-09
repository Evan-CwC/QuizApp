package com.example.quizapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        etNumber1 = findViewById(R.id.et_number1)
        etNumber2 = findViewById(R.id.et_number2)
        btnAdd = findViewById(R.id.btn_add)
        btnSubtract = findViewById(R.id.btn_subtract)
        tvResult = findViewById(R.id.tv_result)

        btnAdd.setOnClickListener { calculate('+') }
        btnSubtract.setOnClickListener { calculate('-') }
    }

    private fun calculate(op: Char) {
        val num1Str = etNumber1.text.toString().trim()
        val num2Str = etNumber2.text.toString().trim()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val num1 = num1Str.toDouble()
            val num2 = num2Str.toDouble()
            val result = if (op == '+') num1 + num2 else num1 - num2
            tvResult.text = "Result: $result"
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
        }
    }
}
