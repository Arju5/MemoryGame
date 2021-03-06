package iss.workshop.sa4108memorygame;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.io.IOException;

public class BackgroundSoundService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.gamebackground);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(50, 50);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Play in the Background",    Toast.LENGTH_SHORT).show();
        return startId;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    public void onLowMemory() {
    }
}