package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    lateinit var btn7 : Button
    lateinit var btn4 : Button
    lateinit var btn12 : Button
    lateinit var btn13 : Button
    var coins=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn12 = findViewById(R.id.button12)
        btn4 = findViewById(R.id.button4)
        btn13 = findViewById(R.id.button13)
        btn7 = findViewById(R.id.btn7)
        Geting_data()
        btn7.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))}

        val  level=intent.extras?.getString("lev")
        if (level=="2") {
            btn4.isVisible = false
            btn12.isVisible = true


        }
    }
    private fun Geting_data():ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        supportFragmentManager.beginTransaction().replace(R.id.con,zaglushka()).commit()
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {
                coins=result.getString("factocoin").toString()


            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }

    private fun write_bd(coin:String){
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {
                coins=result.getString("factocoin").toString()

            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }


        val dataSave:HashMap<String,String> = HashMap<String,String>()
        dataSave.put("factocoin",coin)

        myDocRef.set(dataSave).addOnCompleteListener { task->
            if(task.isSuccessful()){
                Log.d("firebase", "Successful.")
            }
            else  Log.d("firebase", "Error. ${task.exception}")
        }



    }
}