package com.speakebble;

/**
 * Created by william on 1/18/15.
 */
public class PebbleAndroidBridgeDict {

    public static final int BRIDGE_CMD_KEY = 9;
    public static final int BRIDGE_SYN = 0;
    public static final int BRIDGE_SYN_ACK = 1;
    public static final int BRIGE_ACK = 2;

    public static final int ACTION_CMD_KEY = 0;
    public static final int ACTION_START_CAPTURE_VOICE_MSG = 0;
    public static final int ACTION_STOP_CAPTURE_VOICE_MSG = 1;
    public static final int ACTION_SEND_LAST_TEXT = 2;

    public static final int VOICE_RESULT_STATUS_KEY = 5;
    public static final int VOICE_RESULT_SUCCESS = 1;
    public static final int VOICE_RESULT_FAILED = 0;
    public static final int VOICE_RESULT_TEXT_KEY = 4;

}
