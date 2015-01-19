package com.speakebble;

import android.content.Context;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

/**
 * Created by william on 1/18/15.
 */
public class PebbleAndroidBridge {

    private static PebbleAndroidBridge instance;

    private PebbleAndroidBridge() {

    }

    public static PebbleAndroidBridge getInstance() {
        if(instance == null) {
            instance = new PebbleAndroidBridge();
        }

        return instance;
    }

    public void appLaunched(Context context) {

        UUID uuid = Utils.getPebbleUUID(context);

        if (uuid != null) {
            PebbleDictionary data = new PebbleDictionary();
            data.addInt32(PebbleAndroidBridgeDict.BRIDGE_CMD_KEY, PebbleAndroidBridgeDict.BRIDGE_SYN_ACK);
            PebbleKit.sendDataToPebble(context, Utils.getPebbleUUID(context), data);
        }


    }

}
