package com.speakebble;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by william on 1/18/15.
 */
public class SpeechReconManager {

    private static final String SPEECH_RECON_MANAGER_DEBUG = "SPEECH_RECON_MANAGER_DEBUG";

    private static SpeechReconManager instance;
    private SpeechRecognizer speechRecognizer;

    private SpeechReconManager(Context context) {
        // Uses application context. This will prevent app from holding reference to activity context
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context.getApplicationContext());
        speechRecognizer.setRecognitionListener(new SpeechReconListener(context.getApplicationContext()));
    }

    public static SpeechReconManager getInstance(Context context) {
        if (instance == null) {
            instance = new SpeechReconManager(context);
        }

        return instance;
    }

    public void startListening() {
        speechRecognizer.startListening(new Intent());
    }

    public void stopListening() {
        speechRecognizer.stopListening();
    }

    private class SpeechReconListener implements RecognitionListener {

        private Context context;

        public SpeechReconListener(Context context) {
            this.context = context;
        }

        @Override
        public void onReadyForSpeech(Bundle params) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onReadyForSpeech()");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onBeginningOfSpeech()");
        }

        @Override
        public void onRmsChanged(float rmsdB) {
            //Log.d(SPEECH_RECON_MANAGER_DEBUG, "onRmsChanged()");
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onBufferReceived()");
        }

        @Override
        public void onEndOfSpeech() {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onEndOfSpeech()");
        }

        @Override
        public void onError(int error) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onError(). Error Code: " + error);
            PebbleDictionary data = new PebbleDictionary();
            data.addInt32(PebbleAndroidBridgeDict.VOICE_RESULT_STATUS_KEY, PebbleAndroidBridgeDict.VOICE_RESULT_FAILED);
            PebbleKit.sendDataToPebble(context, Utils.getPebbleUUID(context), data);
        }

        @Override
        public void onResults(Bundle results) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onResults()");

            ArrayList<String> resultList = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            Log.d(SPEECH_RECON_MANAGER_DEBUG, resultList.get(0));

            UUID uuid = Utils.getPebbleUUID(context);

            if(uuid != null) {
                // Send result to pebble.
                PebbleDictionary data = new PebbleDictionary();
                data.addString(PebbleAndroidBridgeDict.VOICE_RESULT_TEXT_KEY, resultList.get(0));
                PebbleKit.sendDataToPebble(context, uuid, data);
                PebbleDataReceiver.setLastText(resultList.get(0));
            } else {
                // error occurred...
            }


            Log.d(SPEECH_RECON_MANAGER_DEBUG, resultList.toString());
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onPartialResults()");
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            Log.d(SPEECH_RECON_MANAGER_DEBUG, "onEvent()");
        }
    }

}
