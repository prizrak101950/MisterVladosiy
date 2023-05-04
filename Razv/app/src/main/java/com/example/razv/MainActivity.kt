package com.example.razv

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var btn2 : Button
    lateinit var textView: TextView
    private var playButton4 : ImageView? =null
    private var mPlayer: MediaPlayer? =null
    var count=0
    var coins="100"
    var current_level=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ff)
        btn = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView2)
        Geting_data()
        val  level=intent.extras?.getString("key")
        btn2.setOnClickListener{
            val i=Intent(this,MainActivity3::class.java)
            i.putExtra("lev",level)
            startActivity(i)}
        btn.setOnClickListener{
            val i=Intent(this,MainActivity2::class.java)
            i.putExtra("lev",level)
            startActivity(i)}

        mPlayer=MediaPlayer.create(this,R.raw.pesenka)
        playButton4 = findViewById(R.id.imageView7)
        playButton4?.setImageResource(R.drawable.ic_baseline_music_note_24)
        playButton4?.setOnClickListener{
            if(count==0){
                count=1
                playButton4?.setImageResource(R.drawable.ic_baseline_music_off_24)
                play()
            }
            else{
                playButton4?.setImageResource(R.drawable.ic_baseline_music_note_24)
                mPlayer!!.stop()
                mPlayer!!.prepare()
                count=0
            }
        }




    }


    private fun stopPlay() {
        mPlayer!!.stop()
        mPlayer!!.prepare()


    }


    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer!!.isPlaying) {
            stopPlay()
        }
    }
    fun play() {
        mPlayer!!.start()


    }

    private fun Geting_data():ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game1/progress1")
        myDocRef?.get()?.addOnSuccessListener{ result ->
            if(result.exists()) run {
                current_level = result.getString("level").toString()
                coins=result.getString("factocoin").toString()
                Log.d("my","Уровень $current_level , Фактокоины $coins")
                when(current_level){
                    "1"->{
                        btn2.text="Уровень 1"
                        textView.text="Фактокоины:\n $coins"
                    }
                    "2"->{
                        btn2.text="Уровень 2"
                        textView.text="Фактокоины:\n $coins"
                    }

                    else -> {
                        Log.e("my","Уровень не найден")}
                }

            }
        }
            ?.addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }


}