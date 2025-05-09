package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var btnRetry: Button
    private lateinit var btnFinish: Button

    private var username: String = ""
    private var score: Int = 0
    private var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        tvScore = findViewById(R.id.tv_score)
        btnRetry = findViewById(R.id.btn_retry)
        btnFinish = findViewById(R.id.btn_finish)

        score = intent.getIntExtra("score", 0)
        total = intent.getIntExtra("total", 0)
        username = intent.getStringExtra("username") ?: ""

        tvScore.text = "Your Score: $score / $total"

        btnRetry.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("username", username)
            }
            startActivity(intent)
            finish()
        }

        btnFinish.setOnClickListener {
            finishAffinity()
        }
    }
}
