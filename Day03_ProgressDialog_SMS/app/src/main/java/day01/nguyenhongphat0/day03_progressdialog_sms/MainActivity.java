package day01.nguyenhongphat0.day03_progressdialog_sms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ProgressDialog myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnProgress = findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgressBar = new ProgressDialog(MainActivity.this);
                myProgressBar.setMessage("Loading...");
                myProgressBar.setTitle("Please wait...");
                myProgressBar.setProgressStyle(myProgressBar.STYLE_HORIZONTAL);
                myProgressBar.setProgress(0);
                myProgressBar.setMax(200);
                myProgressBar.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (myProgressBar.getProgress() < myProgressBar.getMax()) {
                                Thread.sleep(10);
                                handler.sendMessage(handler.obtainMessage());
                            }
                            if (myProgressBar.getProgress() == myProgressBar.getMax()) {
                                myProgressBar.dismiss();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    myProgressBar.incrementProgressBy(2);
                }
            };
        });
    }

    public void clickToSendSMS(View view) {
        Intent intent = new Intent(this, SendSMSActivity.class);
        startActivity(intent);
    }
}
