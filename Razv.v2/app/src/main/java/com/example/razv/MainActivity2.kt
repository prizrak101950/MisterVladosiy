package com.example.razv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {
    private lateinit var btn3 : Button
    private lateinit var btn4 : Button
    private lateinit var btn5 : Button
    private lateinit var btn6 : Button
    private lateinit var btn7 : Button
    private var coins=""
    private var knight=""
    private var current_level=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.btn7)
        Geting_data()
        btn7.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))}

        btn3.setOnClickListener { pocupca(coins,"0","0") }
        btn4.setOnClickListener { pocupca(coins,"1500","1") }
        btn5.setOnClickListener {  pocupca(coins,"4000","2")}
        btn6.setOnClickListener { pocupca(coins,"10000","3") }


    }
    private fun Geting_data():ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        myDocRef.get().addOnSuccessListener{ result ->
            if(result.exists()) run {
                coins=result.getString("factocoin").toString()
                knight=result.getString("knight").toString()
               current_level=result.getString("level").toString()

                when(knight){
                    "0"->{proverca()}
                    "1"->{
                        proverca()
                        btn3.text="выбрать" }
                    "2"->{
                        proverca()
                        btn3.text="выбрать"
                        btn4.text="выбрать"}
                    "3"->{
                        proverca()
                        btn3.text="выбрать"
                        btn4.text="выбрать"
                        btn5.text="выбрать"
                    }
                }


            }
        }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }
    private fun proverca(){
        val factocoins=Integer.parseInt(coins)
        if (factocoins>=1500) {
            btn4.text="купить"
        }
        if (factocoins>=4000) {
            btn4.text="купить"
            btn5.text="купить"
        }
        if (factocoins>=10000) {
            btn4.text="купить"
            btn5.text="купить"
            btn6.text="купить"
        }
    }



    private fun write_bd(coin:String,kn:String){
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        myDocRef.get().addOnSuccessListener{ result ->
            if(result.exists()) run {
                coins=result.getString("factocoin").toString()
                knight=result.getString("knight").toString()
                current_level=result.getString("level").toString()

            }
        }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }


        val dataSave:HashMap<String,String> = HashMap<String,String>()
        dataSave.put("factocoin",coin)
        dataSave.put("knight",kn)
        dataSave.put("level",current_level)

        myDocRef.set(dataSave).addOnCompleteListener { task->
            if(task.isSuccessful()){
                Log.d("firebase", "Successful.")
            }
            else  Log.d("firebase", "Error. ${task.exception}")
        }



    }
    private fun pocupca(fc:String,sum:String,kn: String){
        val fc_i=Integer.parseInt(fc)
        val sum_i=Integer.parseInt(sum)
        if(fc_i>=sum_i){
            val ost=fc_i-sum_i
            write_bd(ost.toString(),kn)
            Geting_data()
        }
        else{
            Toast.makeText(this,"Тебе не хватает фактокоинов",Toast.LENGTH_SHORT).show()
        }
    }
}