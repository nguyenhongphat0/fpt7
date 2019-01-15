package day01.nguyenhongphat0.day01

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class PlayMusicService : Service() {
    lateinit var mp: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(this, R.raw.songsao)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }
}
