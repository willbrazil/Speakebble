package com.speakebble;

import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

/**
 * Created by william on 1/18/15.
 */
public class PebbleDataReceiver extends PebbleKit.PebbleDataReceiver {

    private final static String PEBBLE_DATA_RECEIVED_DEBUG = "PEBBLE_DATA_RECEIVED_DEBUG";

    private static String lastText;

    protected PebbleDataReceiver(UUID subscribedUuid) {
        super(subscribedUuid);
    }

    @Override
    public void receiveData(Context context, int i, PebbleDictionary pebbleTuples) {
        Log.d(PEBBLE_DATA_RECEIVED_DEBUG, "Received data.");

        Long actionId = pebbleTuples.getInteger(PebbleAndroidBridgeDict.ACTION_CMD_KEY);

        Log.d(PEBBLE_DATA_RECEIVED_DEBUG, "Command Id: " + actionId);

        if (actionId != null) {
            if(actionId == PebbleAndroidBridgeDict.ACTION_START_CAPTURE_VOICE_MSG) {
                Log.d(PEBBLE_DATA_RECEIVED_DEBUG, "Starting voice capture");
                SpeechReconManager.getInstance(context).startListening();
            } else if(actionId == PebbleAndroidBridgeDict.ACTION_STOP_CAPTURE_VOICE_MSG) {
                Log.d(PEBBLE_DATA_RECEIVED_DEBUG, "Stopping voice capture");
                SpeechReconManager.getInstance(context).stopListening();
            } else if (actionId == PebbleAndroidBridgeDict.ACTION_SEND_LAST_TEXT) {
                SmsManager.getDefault().sendTextMessage("5745142948", null, lastText, null , null);
            }
        } else {
            PebbleAndroidBridge.getInstance().appLaunched(context);
        }

        PebbleKit.sendAckToPebble(context, i);
    }

    public static void setLastText(String text) {
        lastText = text;
    }

}
