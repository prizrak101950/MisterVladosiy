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
    var question2 = 0
    var questions2 = arrayOf<String>("Когда мы прикладываем ракушку к уху мы слышим \n\n 1)шум моря \n\n 2)Шум своей крови \n\n 3)Шум улицы \n\n 4)Свой искажённый голос минуту назад")
    var questions = arrayOf<String>("У слонов с возрастом стираются... \n\n 1)Бивни \n\n 2)Память \n\n 3)Ластик \n" +
            "\n" +
            " 4)Зубы"," Самый сильный орган в теле человека? \n\n 1)Язык \n\n 2)Почки \n\n 3)Глаз \n\n 4)Нижняя челюсть",
            "Сколько надо кричать что-бы нагреть чашку кофе \n\n 1)300 дней \n\n 2)примерно 8 лет \n\n 3)12 лет \n\n 4)2 дня","" +
                "Муровей может поднять груз : \n\n 1)2кг \n\n 2)Который в 50 тяжелее его \n\n 3)Который в 20 раз тяжелее его \n\n 4)700 грамм",
            "Мёд легко перевариварится потому что: \n\n 1)Он состоит из обтикаемых волокон \n\n 2)Это самый экологический продукт \n\n 3)Он на 40 процентов состоит из воска \n\n 4) потому что он уже был переварен пчёлами")

    var rightAnswers = arrayOf<Int>(4,1,2,2,4)



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
            Toast.makeText(applicationContext, "Верно!", Toast.LENGTH_SHORT).show()
            updateQuestion()
        } else {
            Toast.makeText(applicationContext, "Попробуй ещё раз", Toast.LENGTH_SHORT).show()
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("key","0")
            startActivity(i)
        }

    }

    fun updateQuestion() {
        if (questionNo<questions.size-1){
            questionNo = questionNo + 1
            textView.setText(questions.get(questionNo))
        }
        else{
            Toast.makeText(applicationContext, "Ты прошёл 1 уровень", Toast.LENGTH_SHORT).show()
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("key","2")
            startActivity(i)

        }

    }

}