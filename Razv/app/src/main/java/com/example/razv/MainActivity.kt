package com.example.razv

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn11 : Button
    private var playButton4 : ImageView? =null
    private var mPlayer: MediaPlayer? =null
    var count=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ff)
        btn = findViewById(R.id.button)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button11)
        val  level=intent.extras?.getString("key")
        btn3.setOnClickListener{
                val i=Intent(this,MainActivity3::class.java)
                i.putExtra("lev",level)
                startActivity(i)}
        btn2.setOnClickListener{
            val i=Intent(this,MainActivity3::class.java)
            i.putExtra("lev",level)
            startActivity(i)}
        btn.setOnClickListener{
            val i=Intent(this,MainActivity2::class.java)
            i.putExtra("lev",level)
            startActivity(i)}



        if (level=="2"){

            btn2.isVisible=false
            btn3.isVisible=true
            btn3.setOnClickListener{
                startActivity(Intent(this,MainActivity3::class.java))}

        }
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

    fun stop() {
        stopPlay()
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
}