package home.nigel.innovativecinemalistingsappv01;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity { //Splash Activity for the App. Appears upon entry



    private static int SPLASH_TIME_OUT = 1000; //SPLASH Timeout in milliseconds. i.e 1 second
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {              //SPLASH thread
            @Override
            public void run() {         //

                Intent splashIntent = new Intent( SplashActivity.this, MainMenuActivity.class); //Launch Intent to Main Activity
                startActivity(splashIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
