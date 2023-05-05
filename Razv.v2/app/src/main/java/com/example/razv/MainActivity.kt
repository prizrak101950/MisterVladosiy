package com.example.razv

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var btn2 : Button
    private lateinit var textView: TextView
    private var playButton4 : ImageView? =null
    private lateinit var img:ImageView
    private var mPlayer: MediaPlayer? =null
    private var count=0
    private var coins="100"
    private var knight=""
    private var current_level=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ff)
        btn = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        textView = findViewById(R.id.textView2)
        img=findViewById(R.id.imageView6)
        Geting_data()
        btn2.setOnClickListener{
            val i=Intent(this,MainActivity3::class.java)
            startActivity(i)}
        btn.setOnClickListener{
            val i=Intent(this,MainActivity2::class.java)
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

    override fun onStart() {
        super.onStart()
        if(!mPlayer!!.isPlaying){
            count=0
            playButton4?.setImageResource(R.drawable.ic_baseline_music_note_24)
            mPlayer!!.stop()
            mPlayer!!.prepare()

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
    private fun play() {
        mPlayer!!.start()


    }

    @SuppressLint("SetTextI18n")
    private fun Geting_data():ArrayList<ViewModel>{
        val data = ArrayList<ViewModel>()
        val myDocRef: DocumentReference = FirebaseFirestore.getInstance().document("game/progress")
        myDocRef.get().addOnSuccessListener{ result ->
            if(result.exists()) run {
                current_level = result.getString("level").toString()
                coins=result.getString("factocoin").toString()
                knight=result.getString("knight").toString()
                Log.d("my","Уровень $current_level , Фактокоины $coins")

                        btn2.text="Уровень $current_level"
                        textView.text="Фактокоины:\n $coins"
                when(knight){
                    "0"->img.setImageResource(R.drawable.rys)
                    "1"->img.setImageResource(R.drawable.fff)
                    "2"->img.setImageResource(R.drawable.fff2)
                    "3"->img.setImageResource(R.drawable.fff3)

                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w("firebase", "Error getting documents.", exception)
            }
        return data

    }



}