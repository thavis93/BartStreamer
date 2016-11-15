package pl.edu.agh.student.pasek.bartstreamer.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by pasek on 11.11.2016.
 */

public class VideoActivity extends Activity {
    VideoView myvideoView;
    ProgressDialog showProgress;
    public static String url = "";

    public VideoActivity() {
    }

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        this.setContentView(R.layout.activity_videoview);
        Context context = getApplicationContext();
        CharSequence no_url = "Set url first!";
        int duration = Toast.LENGTH_SHORT;

        this.myvideoView = (VideoView)this.findViewById(R.id.myvideoview);
        this.showProgress = new ProgressDialog(this);
        this.showProgress.setMessage("getting Metadata");
        this.showProgress.setCanceledOnTouchOutside(true);
        this.showProgress.show();

        try {
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(this.myvideoView);
            if (url.isEmpty()) {
                Toast toast = Toast.makeText(context, no_url, duration);
                toast.show();
            }
            Uri vidoUlr = Uri.parse(url);
            this.myvideoView.setMediaController(mediaController);
            this.myvideoView.setVideoURI(vidoUlr);
            FirebaseMessaging.getInstance().subscribeToTopic("movementDetection");
        } catch (Exception e) {
            Log.e("Can't set up the video", e.getMessage());
            e.printStackTrace();
        }

        this.myvideoView.requestFocus();
        this.myvideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoActivity.this.showProgress.dismiss();
                VideoActivity.this.myvideoView.start();

            }

        });
        System.out.println(VideoActivity.url);
    }

}
