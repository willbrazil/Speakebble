package com.speakebble;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.UUID;

/**
 * Created by william on 1/15/15.
 */
public class Utils {

    private static final String PEBBLE_UUID_METADATA_TAG = "PEBBLE_UUID";

    private static UUID uuid;


    public static UUID getPebbleUUID(Context context) {

        if(uuid == null) {
            try {
                ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                Bundle bundle = ai.metaData;
                String UUIDString = bundle.getString(PEBBLE_UUID_METADATA_TAG);

                if (UUIDString != null) {
                    uuid = UUID.fromString(UUIDString);
                }

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        return uuid;
    }

}
