package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var tvWelcome: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnSubmit: Button

    private lateinit var questionList: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        tvWelcome = findViewById(R.id.tv_welcome)
        tvQuestion = findViewById(R.id.tv_question)
        progressBar = findViewById(R.id.progressBar)
        rgOptions = findViewById(R.id.rg_options)
        btnSubmit = findViewById(R.id.btn_submit)

        userName = intent.getStringExtra("username") ?: "User"
        tvWelcome.text = "Welcome $userName!"

        questionList = getSampleQuestions()
        loadQuestion()

        btnSubmit.setOnClickListener { checkAnswer() }
    }

    private fun loadQuestion() {
        rgOptions.removeAllViews()
        val question = questionList[currentIndex]
        tvQuestion.text = question.questionText

        for (option in question.options) {
            val rb = RadioButton(this).apply {
                text = option
                setTextColor(Color.BLACK)
                background = ContextCompat.getDrawable(context, R.drawable.option_border)
                setPadding(20, 20, 20, 20)
                layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 24)
                }
            }
            rgOptions.addView(rb)
        }

        updateProgress()
    }

    private fun checkAnswer() {
        val selectedId = rgOptions.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedOption = findViewById<RadioButton>(selectedId)
        val selectedText = selectedOption.text.toString()
        val correctAnswer = questionList[currentIndex].correctAnswer

        for (i in 0 until rgOptions.childCount) {
            val option = rgOptions.getChildAt(i) as RadioButton
            option.setBackgroundColor(Color.TRANSPARENT)
            if (option.text == correctAnswer) {
                option.setBackgroundColor(ContextCompat.getColor(this, R.color.soft_sky_green))
            } else if (option.id == selectedId) {
                option.setBackgroundColor(ContextCompat.getColor(this, R.color.soft_sky_red))
            }
            option.isEnabled = false
        }

        if (selectedText == correctAnswer) score++

        btnSubmit.postDelayed({
            currentIndex++
            if (currentIndex < questionList.size) {
                loadQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("score", score)
                    putExtra("total", questionList.size)
                    putExtra("username", userName)
                }
                startActivity(intent)
                finish()
            }
        }, 1500)
    }

    private fun updateProgress() {
        val progress = (((currentIndex + 1).toDouble() / questionList.size) * 100).toInt()
        progressBar.progress = progress
    }

    private fun getSampleQuestions(): List<Question> {
        return listOf(
            Question("What is Android?", arrayOf("OS", "Browser", "Game"), "OS"),
            Question("Which company created Android?", arrayOf("Apple", "Google", "Microsoft"), "Google"),
            Question("What is Kotlin?", arrayOf("Framework", "Language", "IDE"), "Language"),
            Question("Which layout arranges views vertically or horizontally?", arrayOf("LinearLayout", "ConstraintLayout", "RelativeLayout"), "LinearLayout"),
            Question("Which file contains the app's UI layouts?", arrayOf("MainActivity.java", "AndroidManifest.xml", "XML layout files"), "XML layout files")
        )
    }
}
