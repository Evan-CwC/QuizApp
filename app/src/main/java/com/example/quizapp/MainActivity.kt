package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var buttonStart: Button
    private lateinit var buttonCalculator: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.et_name)
        buttonStart = findViewById(R.id.btn_start)
        buttonCalculator = findViewById(R.id.btn_calculator)


        intent.getStringExtra("username")?.let {
            editTextName.setText(it)
        }

        buttonStart.setOnClickListener {
            val name = editTextName.text.toString().trim()
            if (name.isNotEmpty()) {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("username", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }

        buttonCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
    }
}
