package com.nativecast.chromecast.sdk;

import android.content.Context;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.nativecast.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class CastOptionsProvider implements OptionsProvider{
    @Override
    public CastOptions getCastOptions(Context context) {
        NotificationOptions notificationOptions = new NotificationOptions.Builder()
                .setActions(Arrays.asList(MediaIntentReceiver.ACTION_SKIP_NEXT,
                        MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK,
                        MediaIntentReceiver.ACTION_STOP_CASTING), new int[]{1, 2})
//                .setTargetActivityClassName(ExpandedControlsActivity.class.getName())
                .build();
        CastMediaOptions mediaOptions = new CastMediaOptions.Builder()
//                .setImagePicker(new ImagePickerImpl())
                .setNotificationOptions(notificationOptions)
//                .setExpandedControllerActivityClassName(ExpandedControlsActivity.class.getName())
                .build();
        return new CastOptions.Builder()
                .setReceiverApplicationId(context.getString(R.string.app_id))
                .setCastMediaOptions(mediaOptions)
                .build();
    }

    @Override
    public List<SessionProvider> getAdditionalSessionProviders(Context appContext) {
        return null;
    }
}
