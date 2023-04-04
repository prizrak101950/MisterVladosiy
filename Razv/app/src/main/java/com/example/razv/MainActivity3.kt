package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {


    lateinit var textView: TextView
    lateinit var btn10 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    var questionNo = 0
    var questions = arrayOf<String>("У слонов с возрастом стираются... \n\n 1)Бивни \n\n 2)Память \n\n 3)Ластик \n" +
            "\n" +
            " 4)Зубы"," Самый сильный орган в теле человека? \n\n 1)Язык \n\n 2)Почки \n\n 3)Глаз \n\n 4)Нижняя челюсть")
    var rightAnswers = arrayOf<Int>(4,1)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textView = findViewById(R.id.textView)
        btn10 = findViewById(R.id.button10)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)

        btn10.setOnClickListener {
            showToast(4)
        }
        btn9.setOnClickListener {
            showToast(2)
        }
        btn8.setOnClickListener {
            showToast(3)
        }
        btn7.setOnClickListener {
            showToast(1)
        }





        }
    fun showToast(answer:Int) {
        if (answer==rightAnswers.get(questionNo)) {
            Toast.makeText(applicationContext, "CORRECT!", Toast.LENGTH_SHORT).show()
            updateQuestion()
        } else {
            Toast.makeText(applicationContext, "Попробуй ещё раз", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))

        }

    }

    fun updateQuestion() {
        questionNo = questionNo + 1
        textView.setText(questions.get(questionNo))
    }

}