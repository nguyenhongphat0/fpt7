package day01.nguyenhongphat0.day01

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playButton = findViewById<Button>(R.id.playButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val intent = Intent(this, PlayMusicService::class.java)
        playButton.setOnClickListener { startService(intent) }
        stopButton.setOnClickListener { stopService(intent) }
    }
}
