package day08.nguyenhongphat0.day08

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendNotiButton.setOnClickListener {
            val channelId = "channel08"
            val intent = Intent(this, MainActivity::class.java)
            val stack = TaskStackBuilder.create(this)
            stack.addNextIntent(intent)
            val pendingIntent = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle(notiTitle.text)
                .setContentText("Lorem ipsum dolor sit amer")
                .setContentInfo("Extra information")
                .setSmallIcon(R.drawable.abc_ic_star_black_36dp)
                .setContentIntent(pendingIntent)
                .build()
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, "Testing channel", NotificationManager.IMPORTANCE_HIGH)
                manager.createNotificationChannel(channel)
            }
            manager.notify(0, notification)
        }
    }
}
