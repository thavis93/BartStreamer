package pl.edu.agh.student.pasek.bartstreamer.app;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by pasek on 13.11.2016.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String FirebaseTAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        //update token
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(FirebaseTAG, "New Token: " + refreshToken);


        System.out.println("REGISTRATIONTOKEN: " + refreshToken);
    }
}
