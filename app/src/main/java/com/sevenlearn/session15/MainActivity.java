package com.sevenlearn.session15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT_ACTION_TEST="com.sevenlearn.session15.TEST";
    public static final int REQUEST_CODE_TEST = 10001;
    private ConnectivityBroadcastReceiver connectivityBroadcastReceiver;
    private TextView noInternetConnectionTv;

    private LocalBroadCastReceiverTest localBroadCastReceiverTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadCastReceiverTest = new LocalBroadCastReceiverTest();
        LocalBroadcastManager.getInstance(this).registerReceiver(localBroadCastReceiverTest, new IntentFilter(INTENT_ACTION_TEST));


        connectivityBroadcastReceiver = new ConnectivityBroadcastReceiver();
        registerReceiver(connectivityBroadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        noInternetConnectionTv = findViewById(R.id.tv_main_noInternetConnection);

        Button btnStartActivityForResultTest = findViewById(R.id.btn_main_startActivityForResultTest);
        btnStartActivityForResultTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TEST) {
            if (resultCode == RESULT_OK) {
                String firstName = data.getStringExtra(ResultActivity.EXTRA_KEY_FIRST_NAME);
                String lastName = data.getStringExtra(ResultActivity.EXTRA_KEY_LAST_NAME);

                Toast.makeText(this, firstName + " " + lastName, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                noInternetConnectionTv.setVisibility(View.GONE);
            } else
                noInternetConnectionTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(connectivityBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadCastReceiverTest);
    }

    private static final String TAG = "MainActivity";

    private class LocalBroadCastReceiverTest extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: ");
            String message = intent.getStringExtra("message");
        }
    }
}
