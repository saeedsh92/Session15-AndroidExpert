package com.sevenlearn.session15;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.INTENT_ACTION_TEST);
                intent.putExtra("message", "hello broadcast receiver");
                LocalBroadcastManager.getInstance(App.this).sendBroadcast(intent);
            }
        }, 5000);
    }
}
