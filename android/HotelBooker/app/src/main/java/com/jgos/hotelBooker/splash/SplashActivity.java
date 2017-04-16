package com.jgos.hotelBooker.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jgos.hotelBooker.login.LoginActivity;

/**
 * Created by Bos on 2017-03-04.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //SystemClock.sleep(TimeUnit.SECONDS.toMillis(0));

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}