package com.example.razv

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class level2 : Fragment() {
    private val dataModel:DataModel by activityViewModels()
    private lateinit var textView: TextView
    private lateinit var btn10 : Button
    private lateinit var btn7 : Button
    private lateinit var btn8 : Button
    private lateinit var btn9 : Button
    private var current_level=""
    private var factocoin=""
    private var questionNo = 0

    private var questions = listOf<String>("Когда мы прикладываем ракушку к уху мы слышим \n\n 1)шум моря \n\n 2)Шум своей крови \n\n 3)Шум улицы \n\n 4)Свой искажённый голос минуту назад"
        ,"Единственная планета которая вращается по часовой стрелке это \n\n 1)Юпитер \n\n 2)Венера \n\n 3)Марс \n\n 4)Земля","Что бы поднятся на Эйфелеву башну нужно преодалеть \n\n 1)5034 Ступеники \n\n 2)1792 Ступеньки \n\n 3)579 Ступенек \n\n 4)7390 Ступеньки"
        ,"Сколько нужно времени клетке крови что бы полностью пройти тело человека \n\n 1)Приблизительно 3 минуты \n\n 2)Примерно 40 секунд \n\n 3)Примерно 20 секунд \n\n 4)Минута 45 секунд"
        ,"Единственная сова которая опускает верхние веко что бы моргнуть это \n\n 1)Калибри \n\n 2)Попугай \n\n 3)Страус \n\n 4)Сова")

    private var rightAnswers = listOf<Int>(2,2,2,3,4)
    private val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_level2, container, false)
        textView = view.findViewById(R.id.textView)
        btn10 = view.findViewById(R.id.button10)
        btn7 = view.findViewById(R.id.button7)
        btn8 = view.findViewById(R.id.button8)
        btn9 = view.findViewById(R.id.button9)


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

        return view
    }




    private fun showToast(answer:Int) {
        if (answer== rightAnswers[questionNo]) {
            Toast.makeText(context, "Верно!", Toast.LENGTH_SHORT).show()
            updateQuestion()
        } else {
            Toast.makeText(context, "Попробуй ещё раз", Toast.LENGTH_SHORT).show()
            dataModel.level.value="0"

        }

    }



    private fun updateQuestion() {
        if (questionNo<questions.size-1){
            questionNo = questionNo + 1
            textView.setText(questions[questionNo])
        }
        else{
            Toast.makeText(context, "Ты прошёл 2 уровень", Toast.LENGTH_SHORT).show()
            write_bd()
            dataModel.level.value="0"


        }

    }
    private fun write_bd(){
        myDocRef.get().addOnSuccessListener{ result ->
            if(result.exists()) run {
                current_level = result.getString("level").toString()
                factocoin = result.getString("factocoin").toString()

            }
        }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }



        val dataSave:HashMap<String,String> = HashMap<String,String>()
        dataSave.put("level","3")
        dataSave.put("factocoin","3000")

        myDocRef.set(dataSave).addOnCompleteListener { task->
            if(task.isSuccessful()){
                Log.d("firebase", "Successful.")
            }
            else  Log.d("firebase", "Error. ${task.exception}")
        }



    }
    }
