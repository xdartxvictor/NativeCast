package com.nativecast.chromecast.sdk;

import android.content.Context;
import android.net.Uri;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

/**
 * Created by victoraliaga on 11/3/17.
 */

public class RNCastControls extends ReactContextBaseJavaModule {

    Context context;

    public RNCastControls(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public String getName() {
        return "RNChromecastCastControls";
    }

    /**
     * Send video to Chromecast.
     *
     * @param title Title of the Video
     * @param subtitle SubTitle of the Video
     * @param image_url Image of the Video
     * @param video_url URL of the Video(mp4 format)
     */
    @ReactMethod
    public void loadVideo(final String title, final String subtitle, final String image_url, final String video_url) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                load_video(title, subtitle, image_url, video_url);
            }
        });
    }

    /**
     *
     * return Boolean: ChromeCast Status(true/false)
     */
    @ReactMethod
    public void isChromecastReady(final Callback callback){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (CastContext.getSharedInstance(getReactApplicationContext()).getSessionManager().getCurrentCastSession() != null
                        && CastContext.getSharedInstance(getReactApplicationContext()).getSessionManager().getCurrentCastSession().getRemoteMediaClient() != null) {
                    callback.invoke(true);
                }else{
                    callback.invoke(false);
                }

            }
        });
    }

    /**
     *
     * return Boolean: ChromeCast is Playing(true/false)
     */
    @ReactMethod
    public void isPlaying(final Callback callback){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (is_playing()) {
                    callback.invoke(true);
                }else{
                    callback.invoke(false);
                }

            }
        });
    }

    /**
     *
     * return Boolean: ChromeCast is Playing(true/false)
     */
    @ReactMethod
    public void isPaused(final Callback callback){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (is_paused()) {
                    callback.invoke(true);
                }else{
                    callback.invoke(false);
                }

            }
        });
    }

    /**
     *
     * return Integer: Current Video Position
     */
    @ReactMethod
    private void getCurrentPosition(final Callback callback) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
                Long stream_position =  remoteMediaClient.getApproximateStreamPosition();
                callback.invoke(stream_position.intValue());

            }
        });
    }

    @ReactMethod
    public void playVideo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                play_video();
            }
        });
    }

    @ReactMethod
    public void pauseVideo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pause_video();
            }
        });
    }

    @ReactMethod
    public void stopVideo() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stop_video();
            }
        });
    }

    @ReactMethod
    public void seekToPosition(final Integer position) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seek(position);
            }
        });
    }

    public void load_video(String title, String subtitle, String image_url, String video_url){
        MediaMetadata metadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        metadata.putString(MediaMetadata.KEY_TITLE, title);
        metadata.putString(MediaMetadata.KEY_SUBTITLE, subtitle);
        metadata.addImage(new WebImage(Uri.parse(image_url)));
        MediaInfo mediaInfo = new MediaInfo.Builder(video_url)
                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
                .setContentType("videos/mp4")
                .setMetadata(metadata)
                .build();
        remoteMediaClient.load(mediaInfo, true, 0);
    }

    public void play_video() {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        remoteMediaClient.play();
    }

    public void pause_video() {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        remoteMediaClient.pause();
    }

    public void stop_video() {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        remoteMediaClient.stop();

    }

    public boolean is_playing() {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        return remoteMediaClient.isPlaying();
    }

    public boolean is_paused() {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        return remoteMediaClient.isPaused();
    }

    public void seek(long position) {
        RemoteMediaClient remoteMediaClient = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession().getRemoteMediaClient();
        remoteMediaClient.seek(position);
    }
}
