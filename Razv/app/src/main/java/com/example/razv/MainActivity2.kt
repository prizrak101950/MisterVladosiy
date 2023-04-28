package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.isVisible

class MainActivity2 : AppCompatActivity() {
    lateinit var btn7 : Button
    lateinit var btn4 : Button
    lateinit var btn12 : Button
    lateinit var btn13 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn12 = findViewById(R.id.button12)
        btn4 = findViewById(R.id.button4)
        btn13 = findViewById(R.id.button13)
        btn7 = findViewById(R.id.btn7)
        btn7.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))}

        val  level=intent.extras?.getString("lev")
        if (level=="2") {
            btn4.isVisible = false
            btn12.isVisible = true


        }
    }
}