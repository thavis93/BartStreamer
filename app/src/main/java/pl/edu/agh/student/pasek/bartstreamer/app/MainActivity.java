package pl.edu.agh.student.pasek.bartstreamer.app;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());
        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getId());

        NotificationCompat.Builder myBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setContentTitle("myBuilderTitle")
                .setContentText("myBuilderText");

        //Intent myIntent = new Intent(MainActivity.this, pl.edu.agh.student.pasek.bartstreamer.app.VideoActivity.class);
        //MainActivity.this.startActivity(myIntent);
        Intent myIntent = new Intent(MainActivity.this, VideoActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(VideoActivity.class);
        stackBuilder.addNextIntent(myIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
            );
        myBuilder.setContentIntent(resultPendingIntent);
        NotificationManager myNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        myNotificationManager.notify(0, myBuilder.build());


        MainActivity.this.startActivity(myIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                MainActivity.this.finish();
                MainActivity.this.startActivity(getIntent());
                System.out.println(VideoActivity.url);
                return true;
            case R.id.action_set:
                EditText ip_addr = (EditText) findViewById(R.id.ip_address);
                EditText port = (EditText) findViewById(R.id.port);
                VideoActivity.url = "http://"+ip_addr.getText().toString()+":"+port.getText().toString()+"/";
                System.out.println(VideoActivity.url);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
