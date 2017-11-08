package com.nativecast.chromecast.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.nativecast.chromecast.sdk.RNCastControls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class NativeChromeCastPackage implements ReactPackage {

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new RNCastControls(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.<ViewManager>singletonList( new ChromeCastManager() );    }
}
