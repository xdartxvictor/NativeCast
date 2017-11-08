package com.nativecast.chromecast.sdk;

import android.support.v7.app.MediaRouteButton;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nativecast.R;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class NativeChromeCastButton extends LinearLayout {
    private MediaRouteButton mButton;
    private TextView routes;

    public NativeChromeCastButton(Context context) {
        super(context);
        init();
    }

    public NativeChromeCastButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NativeChromeCastButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.sample_chrome_cast_button, this);
        this.mButton = (MediaRouteButton) findViewById(R.id.media_route_button);
        this.routes = (TextView) findViewById(R.id.routes);

        NativeCastDeviceScanner.registerButton(getContext().getApplicationContext(), mButton);
        routes.setText(mButton.getRouteSelector().toString());
    }
}
