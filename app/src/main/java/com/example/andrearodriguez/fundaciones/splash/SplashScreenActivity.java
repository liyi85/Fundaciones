package com.example.andrearodriguez.fundaciones.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.andrearodriguez.fundaciones.R;
import com.example.andrearodriguez.fundaciones.main.ui.MainActivity;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class SplashScreenActivity extends Activity{
    ImageView imageView;


//    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.imageSplash);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.splash_animation);
        imageView.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });


//        // Set portrait orientation
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        // Hide title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        setContentView(R.layout.activity_splash);
//
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                // Start the next activity
//                Intent mainIntent = new Intent().setClass(SplashScreenActivity.this, LoginActivity.class);
//                startActivity(mainIntent);
//
//                // Close the activity so the user won't able to go back this
//                // activity pressing Back button
//                finish();
//
//            }
//        };
//
//        // Simulate a long loading process on application startup.
//        Timer timer = new Timer();
//        timer.schedule(task, SPLASH_SCREEN_DELAY);
//    }
    }

}
