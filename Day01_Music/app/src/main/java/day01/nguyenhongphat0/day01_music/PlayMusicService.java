package day01.nguyenhongphat0.day01_music;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayMusicService extends Service {
    MediaPlayer mp;

    public PlayMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.songsao);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        return Service.START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy() {
        mp.release();
        super.onDestroy();
    }
}
