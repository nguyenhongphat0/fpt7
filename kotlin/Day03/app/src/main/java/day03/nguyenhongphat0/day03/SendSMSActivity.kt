package day03.nguyenhongphat0.day03

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_send_sms.*
import kotlinx.android.synthetic.main.content_send_sms.*
import java.lang.Exception

class SendSMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_sms)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val phone = findViewById<EditText>(R.id.phoneText).text.toString()
            val content = findViewById<EditText>(R.id.contentText).text.toString()
            val pendingIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), 0)
            val broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context?, intent: Intent?) {
                    Toast.makeText(this@SendSMSActivity, "Result code: " + resultCode, Toast.LENGTH_LONG).show()
                }
            }
            registerReceiver(broadcastReceiver, IntentFilter("SMS_SENT"))
            try {
                val sms = SmsManager.getDefault()
                sms.sendTextMessage(phone, null, content, pendingIntent, pendingIntent)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
            finish()
        }
    }

}
