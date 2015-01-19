package com.speakebble;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;

/**
 * Created by william on 1/15/15.
 */
public class SpeechReconService extends Service {

    PebbleDataReceiver pebbleDataReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("PEBBLE_ANDROID_SERVICE", "Service launched....");

        // Assuming uuid will never be null... prototype only
        pebbleDataReceiver = new PebbleDataReceiver(Utils.getPebbleUUID(getApplicationContext()));

        PebbleKit.registerReceivedDataHandler(getApplicationContext(), pebbleDataReceiver);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(pebbleDataReceiver);
        super.onDestroy();
    }
}
