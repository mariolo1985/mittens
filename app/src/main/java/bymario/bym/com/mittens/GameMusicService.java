package bymario.bym.com.mittens;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class GameMusicService extends Service {
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.playgame);
        mp.setLooping(false);
        mp.setVolume(100,100);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();

        return 1;
    }

}
