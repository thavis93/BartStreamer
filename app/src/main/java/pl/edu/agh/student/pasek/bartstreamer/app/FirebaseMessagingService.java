package pl.edu.agh.student.pasek.bartstreamer.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by pasek on 13.11.2016.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String FMessService = "MyFirebaseMessagigngService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(FMessService, "FROM: " + remoteMessage.getFrom());

        //Check if the message is empty
        if (remoteMessage.getData().size() > 0) {
            Log.d(FMessService, "Message data: " + remoteMessage.getData());
        }

        //Check if message contains information
        if (remoteMessage.getNotification() != null) {
            Log.d(FMessService, "Message body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

        super.onMessageReceived(remoteMessage);
    }
//Display notification
    private void sendNotification(String body) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /*Request code */, intent, PendingIntent.FLAG_ONE_SHOT);
        //set Sound Notification
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notoficationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.barticon)
                .setContentTitle("Move Detected")
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of Notification*/, notoficationBuilder.build());
    }
}
