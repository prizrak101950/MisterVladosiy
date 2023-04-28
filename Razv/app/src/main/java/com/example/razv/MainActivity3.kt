package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class MainActivity3 : AppCompatActivity() {


    lateinit var textView: TextView
    lateinit var btn10 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    var questionNo = 0
    var questions2 = listOf<String>("Когда мы прикладываем ракушку к уху мы слышим \n\n 1)шум моря \n\n 2)Шум своей крови \n\n 3)Шум улицы \n\n 4)Свой искажённый голос минуту назад"
    ,"Единственная планета которая вращается по часовой стрелке это \n\n 1)Юпитер \n\n 2)Венера \n\n 3)Марс \n\n 4)Земля","Что бы поднятся на Эйфелеву башну нужно преодалеть \n\n 1)5034 Ступеники \n\n 2)1792 Ступеньки \n\n 3)579 Ступенек \n\n 4)7390 Ступеньки"
    ,"Сколько нужно времени клетке крови что бы полностью пройти тело человека \n\n 1)Приблизительно 3 минуты \n\n 2)Примерно 40 секунд \n\n 3)Примерно 20 секунд \n\n 4)Минута 45 секунд"
    ,"Единственная сова которая опускает верхние веко что бы моргнуть это \n\n 1)Калибри \n\n 2)Попугай \n\n 3)Страус \n\n 4)Сова")
    var questions = listOf<String>("У слонов с возрастом стираются... \n\n 1)Бивни \n\n 2)Память \n\n 3)Ластик \n" +
            "\n" +
            " 4)Зубы"," Самый сильный орган в теле человека? \n\n 1)Язык \n\n 2)Почки \n\n 3)Глаз \n\n 4)Нижняя челюсть",
            "Сколько надо кричать что-бы нагреть чашку кофе \n\n 1)300 дней \n\n 2)примерно 8 лет \n\n 3)12 лет \n\n 4)2 дня","" +
                "Муравей может поднять груз : \n\n 1)2кг \n\n 2)Который в 50 тяжелее его \n\n 3)Который в 20 раз тяжелее его \n\n 4)700 грамм",
            "Мёд легко перевариварится потому что: \n\n 1)Он состоит из обтекаемых волокон \n\n 2)Это самый экологический продукт \n\n 3)Он на 40 процентов состоит из воска \n\n 4) потому что он уже был переварен пчёлами")

    var rightAnswers = listOf<Int>(4,1,2,2,4)
    var rightAnswers2 = listOf<Int>(2,2,2,3,4)
    val  level=intent.extras?.getString("lev")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        textView = findViewById(R.id.textView)
        btn10 = findViewById(R.id.button10)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)

        if (level=="2") {
            textView.setText(questions2.get(questionNo))
            btn10.setOnClickListener {
                showToast(4,questions2,rightAnswers2)
            }
            btn9.setOnClickListener {
                showToast(2,questions2,rightAnswers2)
            }
            btn8.setOnClickListener {
                showToast(3,questions2,rightAnswers2)
            }
            btn7.setOnClickListener {
                showToast(1,questions2,rightAnswers2)
            }
        }
        else{
            btn10.setOnClickListener {
                showToast(4,questions,rightAnswers)
            }
            btn9.setOnClickListener {
                showToast(2,questions,rightAnswers)
            }
            btn8.setOnClickListener {
                showToast(3,questions,rightAnswers)
            }
            btn7.setOnClickListener {
                showToast(1,questions,rightAnswers)
            }
        }





        }





    fun showToast(answer:Int,q:List<String>,r:List<Int>) {
        if (answer==r.get(questionNo)) {
            Toast.makeText(applicationContext, "Верно!", Toast.LENGTH_SHORT).show()
            updateQuestion(q)
        } else {
            Toast.makeText(applicationContext, "Попробуй ещё раз", Toast.LENGTH_SHORT).show()
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("key","0")
            startActivity(i)
        }

    }

    fun updateQuestion(q:List<String>) {
        if (questionNo<q.size-1){
            questionNo = questionNo + 1
            textView.setText(q.get(questionNo))
        }
        else{
            Toast.makeText(applicationContext, "Ты прошёл $level уровень", Toast.LENGTH_SHORT).show()
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("key","2")
            startActivity(i)

        }

    }

}