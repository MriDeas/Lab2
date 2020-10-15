package com.dan.learn.lab2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/mimixiche/compressScanPicture.jpg");
        printBitmap(bitmap);
    }


    private void printBitmap(Bitmap bitmap) {
        if (bitmap == null) return;
        int density = bitmap.getDensity();
        int byteCount = bitmap.getByteCount();
        int allocationByteCount = bitmap.getAllocationByteCount();
        ColorSpace colorSpace = bitmap.getColorSpace();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap.Config config = bitmap.getConfig();
        String configName = config != null ? config.name() : "";
        ColorSpace.Model model = colorSpace != null ? colorSpace.getModel() : null;
        String colorSpaceName = model != null ? model.name() : "";

//        byte[] ninePatchChunk = bitmap.getNinePatchChunk();
        int rowBytes = bitmap.getRowBytes();
        int pixel = bitmap.getPixel(100, 100);
//        int scaledWidth = bitmap.getScaledWidth();

        String bitmapInfo = "density :" + density
                + " width:" + width + " height:" + height
                + " rowBytes:" + rowBytes + " 100 pix:" + pixel
                + " byteCount:" + byteCount
                + " allocationByteCount:" + allocationByteCount
                + " configName:" + configName
                + " colorSpaceName:" + colorSpaceName;
        System.out.println("打印 ------------ " + bitmapInfo);

//        bitmap.compress();
//        bitmap.copy();
//        bitmap.describeContents();
//        bitmap.eraseColor();
//        bitmap.hasAlpha();
//        bitmap.hasMipMap();
//        bitmap.isMutable();
//        bitmap.isPremultiplied();
//        bitmap.isRecycled();

    }

    private void printBitmapOptions() {
        BitmapFactory.Options options = new BitmapFactory.Options();

        Bitmap inBitmap = options.inBitmap;//
        int inSampleSize = options.inSampleSize;
        boolean inJustDecodeBounds = options.inJustDecodeBounds;
        int inDensity = options.inDensity;
        boolean inMutable = options.inMutable;
        ColorSpace inPreferredColorSpace = options.inPreferredColorSpace;
        byte[] inTempStorage = options.inTempStorage;
        int inTargetDensity = options.inTargetDensity;
        int inScreenDensity = options.inScreenDensity;
        boolean inPremultiplied = options.inPremultiplied;
        Bitmap.Config inPreferredConfig = options.inPreferredConfig;
        boolean inScaled = options.inScaled;
    }

    private void bitmapFactoryDecode() {
//        BitmapFactory.decodeStream();
//        BitmapFactory.decodeFile();
//        BitmapFactory.decodeResource();
//        BitmapFactory.decodeByteArray();
//        BitmapFactory.decodeFileDescriptor();
//        BitmapFactory.decodeResourceStream();
    }

}