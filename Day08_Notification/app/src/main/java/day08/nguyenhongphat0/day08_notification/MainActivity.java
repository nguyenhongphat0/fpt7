package day08.nguyenhongphat0.day08_notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private NotificationManager manager;
    private int notiID = 6789;
    private int numMsg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickToSend(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel08");
        builder.setContentTitle("Content title");
        builder.setContentText("Content text: Message has been received");
        builder.setTicker("Ticker: Message Alert");
        builder.setSmallIcon(R.drawable.ic_action_unread);
        builder.setNumber(++numMsg);
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        TaskStackBuilder stack = TaskStackBuilder.create(this);
        stack.addNextIntent(intent);
        PendingIntent pendingIntent = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel chanel = new NotificationChannel("channel08", "Testing notification channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(chanel);
        }
        manager.notify(numMsg, builder.build());
    }

    public void clickToCancel(View view) {
        manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notiID);
    }

    public void clickToUpdate(View view) {
    }

    public void clickToSendMultipleTime(View view) {
        String channelID = "channel08";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID);
        builder.setContentTitle("Content title" + numMsg);
        builder.setContentText("Content text: Message has been received" + numMsg);
        builder.setTicker("Ticker: Message Alert");
        builder.setSmallIcon(R.drawable.ic_action_unread);
        builder.setNumber(++numMsg);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = {"HP+", "Mana+", "Luck+"};
        inboxStyle.setBigContentTitle("LV UP");
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        builder.setStyle(inboxStyle);
        Intent intent = new Intent(this, NotificationDetailActivity.class);
        TaskStackBuilder stack = TaskStackBuilder.create(this);
        stack.addNextIntent(intent);
        PendingIntent pendingIntent = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel(channelID, "Multiple Notify", NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(notiID, builder.build());
    }
}
