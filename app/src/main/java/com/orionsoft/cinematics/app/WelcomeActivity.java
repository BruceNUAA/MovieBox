package com.orionsoft.cinematics.app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

public class WelcomeActivity extends AppCompatActivity {

    private LinearLayout upLL , downLL;
    private Animation uptodown,downtoup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // transparent status bar
        StatusBarUtil.setTransparent(WelcomeActivity.this);

        //find view by id
        upLL = findViewById(R.id.up);
        downLL = findViewById(R.id.down);

        // up down animation
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        upLL.setAnimation(uptodown);
        downLL.setAnimation(downtoup);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(WelcomeActivity.this,
                        MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, 1000);

    }

    public void checkConnection( Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
            }
        } else {
            // not connected to the internet
            Toast.makeText(context, "Sorry, no internet connectivity detected.", Toast.LENGTH_LONG).show();

        }
    }
}
