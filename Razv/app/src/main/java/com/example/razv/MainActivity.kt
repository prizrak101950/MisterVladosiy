package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button11)
        btn2.setOnClickListener{
            startActivity(Intent(this,MainActivity3::class.java))}
        btn.setOnClickListener{
            startActivity(Intent(this,MainActivity2::class.java))}

        val  level=intent.extras?.getString("key")
        if (level=="2"){
            btn2.isVisible=false
            btn3.isVisible=true
            btn3.setOnClickListener{
                startActivity(Intent(this,MainActivity3::class.java))}


        }

    }
}