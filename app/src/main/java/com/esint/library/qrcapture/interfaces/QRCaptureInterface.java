package com.esint.library.qrcapture.interfaces;

/**
 * 解码接口
 * Created by MX on 2017/5/5.
 */

public interface QRCaptureInterface {
    /**
     * 重启预览
     */
    public void restartPreview();

    /**
     * 解析成功
     */
    public void decodeSuccess();

    /**
     * 解析失败
     */
    public void decodeFail();
}
