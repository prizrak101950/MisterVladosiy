package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {
    lateinit var btn:Button
    var current_level=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        btn=findViewById(R.id.button14)
        Geting_data()
        btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        }


    private fun Geting_data():ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        supportFragmentManager.beginTransaction().replace(R.id.con,zaglushka()).commit()
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {
               current_level = result.getString("level").toString()

                when(current_level){
                    "1"->supportFragmentManager.beginTransaction().replace(R.id.con,level1()).commit()
                    "2"->supportFragmentManager.beginTransaction().replace(R.id.con,level2()).commit()
                    else -> {Log.e("my","Уровень не найден")}
                }

            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }



}