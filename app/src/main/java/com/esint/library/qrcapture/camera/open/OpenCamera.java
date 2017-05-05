package com.esint.library.qrcapture.camera.open;


import android.hardware.Camera;

/**
 * Created by MX on 2017/5/4.
 */

public final class OpenCamera {
    private final int index;
    /**
     * 相机实例
     */
    private final Camera camera;
    /**
     * 摄像头前后
     */
    private final CameraFacing facing;
    /**
     * 屏幕横竖
     */
    private final int orientation;

    public OpenCamera(int index, Camera camera, CameraFacing facing, int orientation) {
        this.index = index;
        this.camera = camera;
        this.facing = facing;
        this.orientation = orientation;
    }

    public Camera getCamera() {
        return camera;
    }

    /**
     * 獲得前後攝像頭
     * @return
     */
    public CameraFacing getFacing() {
        return facing;
    }

    /**
     * 获得屏幕方向
     * @return
     */
    public int getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "Camera #" + index + " : " + facing + ',' + orientation;
    }
}
