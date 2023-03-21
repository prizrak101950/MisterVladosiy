package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    lateinit var btn10 : Button
    var questionNo = 5
    var questions = arrayOf<String>("У слонов с возрастом стираются... \n\n 1)Бивни \n\n 2)Память \n\n 3)Ластик \n" +
            "\n" +
            " 4)Зубы"," Самый сильный орган в теле человека? \n\n 1)Язык \n\n 2)Почки \n\n 3)Глаз \n\n 4)Нижняя челюсть")
    var rightAnswers = arrayOf<String>("Зубы","Язык")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        btn10 = findViewById(R.id.button10)
        btn10.setOnClickListener {
            startActivity(Intent(this,MainActivity4::class.java))}


    }
}