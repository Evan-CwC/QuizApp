package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var editTextName: EditText? = null
    var buttonStart: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.et_name)
        buttonStart = findViewById(R.id.btn_start)

        val passedName = intent.getStringExtra("username")
        if (passedName != null) {
            editTextName.setText(passedName)
        }

        buttonStart.setOnClickListener(View.OnClickListener { v: View? ->
            val name = editTextName.getText().toString().trim { it <= ' ' }
            if (!name.isEmpty()) {
                val intent = Intent(
                    this@MainActivity,
                    QuizActivity::class.java
                )
                intent.putExtra("username", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        })

        val btnCalculator = findViewById<Button>(R.id.btn_calculator)
        btnCalculator.setOnClickListener { v: View? ->
            val intent = Intent(
                this@MainActivity,
                CalculatorActivity::class.java
            )
            startActivity(intent)
        }
    }
}
