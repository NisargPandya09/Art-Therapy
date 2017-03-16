package csulb.hw3;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Eraser extends IntentService {
    private boolean flag = false;
    public Eraser() {
        super("HelloIntentService");
    }

    protected void onHandleIntent(Intent intent) {

        MediaPlayer mp = MediaPlayer.create(Eraser.this,R.raw.eraser);
        mp.start();
        while(mp.isPlaying())
        {


            System.out.println();


        }
        mp.reset();
        mp.release();

    }

}

