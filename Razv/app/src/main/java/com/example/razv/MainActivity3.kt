package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity

class MainActivity3 : AppCompatActivity() {
    private val dataModel:DataModel by viewModels()
    var lev="0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        when(lev){
            "1"->supportFragmentManager.beginTransaction().replace(R.id.cont,level1()).commit()
            "2"->supportFragmentManager.beginTransaction().replace(R.id.cont,level2()).commit()
            "0"->supportFragmentManager.beginTransaction().replace(R.id.cont,level1()).commit()
        }

        dataModel.level.observe(this,
            {
            lev=it

            })

        }





}