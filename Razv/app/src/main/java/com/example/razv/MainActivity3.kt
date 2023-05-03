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
    private val dataModel:DataModel by viewModels()
    private val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
    var level:String="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        write_bd("level")



        }

    private fun write_bd(collection:String){
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {
                val current_level: String? = result.getString(collection)
                level=current_level.toString()
            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }

        var next=Integer.parseInt(level)
        next++
        level=next.toString()

        val dataSave:HashMap<String,String> = HashMap<String,String>()
        dataSave.put("level",level)

        myDocRef.set(dataSave).addOnCompleteListener { task->
            if(task.isSuccessful()){
                Log.d("firebase", "Successful.")
            }
            else  Log.d("firebase", "Error. ${task.exception}")
        }
        dataModel.level.value=level
        when(level){
            "1"->supportFragmentManager.beginTransaction().replace(R.id.cont,level1()).commit()
            "2"->supportFragmentManager.beginTransaction().replace(R.id.cont,level2()).commit()
            "0"->supportFragmentManager.beginTransaction().replace(R.id.cont,level1()).commit()
        }

    }


    private fun Geting_data(themes:String):ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("themes/$themes")
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {



            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }


}