package com.nativecast.chromecast.sdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.MediaRouteButton;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.CastState;
import com.google.android.gms.cast.framework.CastStateListener;
import com.google.android.gms.cast.framework.SessionManagerListener;

/**
 * Created by victoraliaga on 10/26/17.
 */

public class NativeCastDeviceScanner {

    private CastContext mCastContext;
    private CastSession mCastSession;

    private CastStateListener mCastStateListener;
    private SessionManagerListener<CastSession> mSessionManagerListener;

    private void setUpCastContext(Context context) {
        mCastContext = CastContext.getSharedInstance(context);
    }

    private static void setUpCastButton(Context context, MediaRouteButton mediaRouteButton) {
        CastButtonFactory.setUpMediaRouteButton(context, mediaRouteButton);
    }


    private static MenuItem setUpCastButton(Context context, Menu menu, int buttonId) {
        return CastButtonFactory.setUpMediaRouteButton(context, menu, buttonId);
    }

    private void getCurrentCastSession(Context context) {
        if (mCastSession == null) {
            mCastSession = CastContext.getSharedInstance(context).getSessionManager()
                    .getCurrentCastSession();
        }
    }

    private void addListeners(CastScanListener listener, SessionStateListener sessionStateListener) {
        mSessionManagerListener = setUpSessionManagerListener(sessionStateListener);
        mCastStateListener = setUpCastStateListener(listener);
        mCastContext.addCastStateListener(mCastStateListener);
        mCastContext.getSessionManager().addSessionManagerListener(
                mSessionManagerListener, CastSession.class);
    }

    private void removeListeners() {
        mCastContext.removeCastStateListener(mCastStateListener);
        mCastContext.getSessionManager().removeSessionManagerListener(
                mSessionManagerListener, CastSession.class);
    }

    public void setUp(Context context) {
        setUpCastContext(context);
    }

    public static void registerButton(Context context, MediaRouteButton mediaRouteButton) {
        setUpCastButton(context, mediaRouteButton);
    }

    public static MenuItem registerMenu(Context context, Menu menu, int mediaRouteButton) {
        return setUpCastButton(context, menu, mediaRouteButton);
    }

    public void stopScanning() {
        removeListeners();
    }

    public void startScanning(Context context, CastScanListener castScanListener, SessionStateListener sessionStateListener) {
        addListeners(castScanListener, sessionStateListener);
        getCurrentCastSession(context);
    }

    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        return mCastContext.onDispatchVolumeKeyEventBeforeJellyBean(event);
    }

    @NonNull
    private CastStateListener setUpCastStateListener(final CastScanListener listener) {
        return new CastStateListener() {
            @Override
            public void onCastStateChanged(int newState) {
                switch (newState) {
                    case CastState.NO_DEVICES_AVAILABLE:
                        listener.onNoDevicesAvailable();
                        break;

                    case CastState.NOT_CONNECTED:
                        listener.onDeviceNotConnected();
                        break;

                    case CastState.CONNECTED:
                        listener.onDeviceConnected();
                        break;

                    case CastState.CONNECTING:
                        listener.onDeviceConnecting();
                        break;
                }
            }
        };
    }

    @NonNull
    private SessionManagerListener<CastSession> setUpSessionManagerListener(final SessionStateListener listener) {
        return new SessionManagerListener<CastSession>() {
            @Override
            public void onSessionStarting(CastSession castSession) {
                listener.onSessionStarting();
            }

            @Override
            public void onSessionStarted(CastSession castSession, String s) {
                mCastSession = castSession;

                listener.onSessionStarted(s);
            }

            @Override
            public void onSessionStartFailed(CastSession castSession, int i) {
                listener.onSessionStartFailed(i);
            }

            @Override
            public void onSessionEnding(CastSession castSession) {
                listener.onSessionEnding();

            }

            @Override
            public void onSessionEnded(CastSession castSession, int i) {
                if (castSession == mCastSession) {
                    mCastSession = null;
                }
                listener.onSessionEnded(i);
            }

            @Override
            public void onSessionResuming(CastSession castSession, String s) {
                listener.onSessionResuming(s);
            }

            @Override
            public void onSessionResumed(CastSession castSession, boolean b) {
                mCastSession = castSession;
                listener.onSessionResumed(b);
            }

            @Override
            public void onSessionResumeFailed(CastSession castSession, int i) {
                listener.onSessionResumeFailed(i);
            }

            @Override
            public void onSessionSuspended(CastSession castSession, int i) {
                listener.onSessionSuspended(i);

            }
        };
    }

    public interface CastScanListener {
        void onNoDevicesAvailable();

        void onDeviceConnecting();

        void onDeviceConnected();

        void onDeviceNotConnected();

    }

    public interface SessionStateListener {
        void onSessionStarting();

        void onSessionStarted(String var2);

        void onSessionStartFailed(int var2);

        void onSessionEnding();

        void onSessionEnded(int var2);

        void onSessionResuming(String var2);

        void onSessionResumed(boolean var2);

        void onSessionResumeFailed(int var2);

        void onSessionSuspended(int var2);
    }
}
