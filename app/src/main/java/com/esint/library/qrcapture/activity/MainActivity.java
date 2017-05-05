package com.esint.library.qrcapture.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.esint.library.qrcapture.R;
import com.esint.library.qrcapture.camera.CameraManager;
import com.esint.library.qrcapture.widget.ViewfinderView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  SurfaceHolder.Callback{
    private final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ViewfinderView mViewfinderView;
    private CameraManager mCameraManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();

        mViewfinderView.setCameraManager(mCameraManager);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void initData(){
        mContext = MainActivity.this;
        mCameraManager = new CameraManager(mContext);
    }

    private void initView(){
        mSurfaceView = (SurfaceView)findViewById(R.id.sv_mainActivity_surface);
        mViewfinderView = (ViewfinderView)findViewById(R.id.vv_mainActivity_finder);
    }

    private void initEvent(){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        initCamera(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (mCameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            mCameraManager.openDriver(surfaceHolder);
            mCameraManager.startPreview();
            // Creating the handler starts the preview, which can also throw a RuntimeException.
           /* if (handler == null) {
                handler = new CaptureActivityHandler(this, decodeFormats, decodeHints, characterSet, cameraManager);
            }
            decodeOrStoreSavedBitmap(null, null);*/
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
//            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
//            displayFrameworkBugMessageAndExit();
        }
    }
}
