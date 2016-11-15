package bymario.bym.com.mittens;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by MLo on 11/15/2016.
 */
public class ScareMusicService extends Service {
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.scare);
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
