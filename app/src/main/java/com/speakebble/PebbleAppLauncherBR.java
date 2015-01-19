package com.speakebble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

/**
 * Created by william on 1/15/15.
 */
public class PebbleAppLauncherBR extends BroadcastReceiver {

    private static final String APP_LAUNCHER_DEBUG = "APP_LAUNCHER_DEBUG";
    private static boolean hasLaunched = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(APP_LAUNCHER_DEBUG, "Received intent");

        Bundle bundle = intent.getExtras();
        UUID uuid = (UUID) bundle.get("uuid");

        if (!hasLaunched && uuid.toString().equals(Utils.getPebbleUUID(context).toString())) {
            Log.d(APP_LAUNCHER_DEBUG, "Launching service...");
            context.startService(new Intent(context, SpeechReconService.class));
            hasLaunched = true;
            PebbleAndroidBridge.getInstance().appLaunched(context);
        }

    }
}
