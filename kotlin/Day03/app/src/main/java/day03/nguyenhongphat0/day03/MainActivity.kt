package day03.nguyenhongphat0.day03

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goSendSMSButton = findViewById<Button>(R.id.goSendSMSButton)
        goSendSMSButton.setOnClickListener {view ->
            startActivity(Intent(this@MainActivity, SendSMSActivity::class.java))
        }

        val loadingButton = findViewById<Button>(R.id.loadingButton)
        loadingButton.setOnClickListener {
            Toast.makeText(this, "Showing loading dialog", Toast.LENGTH_SHORT)
            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait...")
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            progressDialog.max = 100
            progressDialog.show()
            var handler = object : Handler() {
                override fun handleMessage(msg: Message?) {
                    super.handleMessage(msg)
                    progressDialog.incrementProgressBy(msg!!.what)
                }
            }
            Thread(Runnable {
                while (progressDialog.progress < progressDialog.max) {
                    println(progressDialog.progress)
                    handler.sendEmptyMessage(3)
                    Thread.sleep(100)
                }
                progressDialog.dismiss()
            }).start()
        }
    }
}
