package com.nativecast.chromecast.sdk;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.Menu;

import com.nativecast.R;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class CastManager {
    private NativeCastDeviceScanner castDeviceScanner;


    public void onCreate(Context context){
        castDeviceScanner = new NativeCastDeviceScanner();
        castDeviceScanner.setUp(context);

    }


    public void onResume(Context context){
        castDeviceScanner.startScanning(context, new NativeCastDeviceScanner.CastScanListener() {
                    @Override
                    public void onNoDevicesAvailable() {
                    }

                    @Override
                    public void onDeviceConnecting() {
//                showIntroductoryOverlay(activity);
                    }

                    @Override
                    public void onDeviceConnected() {
//                showIntroductoryOverlay(activity);
                    }

                    @Override
                    public void onDeviceNotConnected() {
//                showIntroductoryOverlay(activity);
                    }
                },
                new NativeCastDeviceScanner.SessionStateListener() {
                    @Override
                    public void onSessionStarting() {

                    }

                    @Override
                    public void onSessionStarted(String var2) {

                    }

                    @Override
                    public void onSessionStartFailed(int var2) {

                    }

                    @Override
                    public void onSessionEnding() {

                    }

                    @Override
                    public void onSessionEnded(int var2) {

                    }

                    @Override
                    public void onSessionResuming(String var2) {

                    }

                    @Override
                    public void onSessionResumed(boolean var2) {

                    }

                    @Override
                    public void onSessionResumeFailed(int var2) {

                    }

                    @Override
                    public void onSessionSuspended(int var2) {

                    }
                });
    }

    public void onPause(){
        castDeviceScanner.stopScanning();
    }



    public boolean dispatchKeyEvent(@NonNull KeyEvent event){
        return castDeviceScanner.dispatchKeyEvent(event);
    }


    public boolean onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.menu, menu);
        NativeCastDeviceScanner.registerMenu(activity, menu,  R.id.media_route_menu_item);
        return true;
    }

}
