package day01.nguyenhongphat0.day03_progressdialog_sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
    }

    public void clickToSend(View view) {
        EditText phone = findViewById(R.id.txtPhone);
        EditText content = findViewById(R.id.txtContent);
        final SmsManager sms = SmsManager.getDefault();
        Intent intent = new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Sent OK";
                if (result != Activity.RESULT_OK) {
                    msg = "Sent Failed";
                }
                Toast.makeText(SendSMSActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter("ACTION_MSG_SENT"));
        sms.sendTextMessage(phone.getText().toString(), null, content.getText().toString(), pendingIntent, null);
        finish();
    }
}
