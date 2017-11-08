package com.nativecast.chromecast.react;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.nativecast.chromecast.sdk.NativeChromeCastButton;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class ChromeCastManager extends SimpleViewManager<NativeChromeCastButton> {

    public static final String REACT_CLASS = "RCTChromeCastButton";
    private static final int HOOK_UP = 1;


    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected NativeChromeCastButton createViewInstance(ThemedReactContext reactContext) {
        return new NativeChromeCastButton(reactContext);
    }
}
