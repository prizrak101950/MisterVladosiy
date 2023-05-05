package com.example.razv

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class level1 : Fragment() {
    private val dataModel:DataModel by activityViewModels()
    private var current_level=""
    private var factocoin=""
    private lateinit var textView: TextView
    private lateinit var btn10 : Button
    private lateinit var btn7 : Button
    private lateinit var btn8 : Button
    private lateinit var btn9 : Button
    private var questionNo = 0
    private val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")

    private var questions = listOf<String>("У слонов с возрастом стираются... \n\n 1)Бивни \n\n 2)Память \n\n 3)Ластик \n" +
            "\n" +
            " 4)Зубы"," Самый сильный орган в теле человека? \n\n 1)Язык \n\n 2)Почки \n\n 3)Глаз \n\n 4)Нижняя челюсть",
        "Сколько надо кричать что-бы нагреть чашку кофе \n\n 1)300 дней \n\n 2)примерно 8 лет \n\n 3)12 лет \n\n 4)2 дня","" +
                "Муравей может поднять груз : \n\n 1)2кг \n\n 2)Который в 50 тяжелее его \n\n 3)Который в 20 раз тяжелее его \n\n 4)700 грамм",
        "Мёд легко перевариварится потому что: \n\n 1)Он состоит из обтекаемых волокон \n\n 2)Это самый экологический продукт \n\n 3)Он на 40 процентов состоит из воска \n\n 4) потому что он уже был переварен пчёлами")

    private var rightAnswers = listOf<Int>(4,1,2,2,4)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_level1, container, false)
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
            Toast.makeText(context, "Ты прошёл 1 уровень", Toast.LENGTH_SHORT).show()
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
        dataSave.put("level","2")
        dataSave.put("factocoin","1500")

        myDocRef.set(dataSave).addOnCompleteListener { task->
            if(task.isSuccessful()){
                Log.d("firebase", "Successful.")
            }
            else  Log.d("firebase", "Error. ${task.exception}")
        }



    }



}