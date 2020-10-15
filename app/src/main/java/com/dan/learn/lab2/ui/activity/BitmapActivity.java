package com.dan.learn.lab2.ui.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.dan.learn.lab2.R;
import com.dan.learn.lab2.ui.base.BaseActivity;
import com.dan.learn.lab2.utils.SizeUtil;
import com.dan.learn.lab2.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;

@RequiresApi(api = Build.VERSION_CODES.O)
public class BitmapActivity extends BaseActivity {

    @BindView(R.id.iv_bitmap)
    ImageView iv_bitmap;
    @BindView(R.id.iv_image_2)
    ImageView iv_image_2;
    @BindView(R.id.tv_bitmap_info)
    TextView tv_bitmap_info;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
    }

    public void loadBitmap(View v) {
//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/WeiXin";
//        File dir = new File(path);
//        String[] list = dir.list();
//        String picturePath = "";
//        if (list != null && list.length > 1) {
//            picturePath = list[1];
//        }

        String path = "/storage/emulated/0/mimixiche/compressScanPicture.jpg";

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        iv_bitmap.setImageBitmap(bitmap);
        printBitmap(bitmap);
    }

    public void operation2(View v) {
        String path = "/storage/emulated/0/mimixiche/compressScanPicture.jpg";
//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()
//                + "/WeiXin/mmexport1595418727147.jpg"; //mmexport1602744612579.png

        byte[] tempStorage = new byte[16 * 1024];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inTempStorage = tempStorage; //即使不设置，系统仍将设置大小为16KB的临时存储
        options.inPremultiplied = false;
        options.inMutable = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inDensity = 320; // 将直接影响加载出的Bitmap的density值;如果inDensity==0，Bitmap的density将采用设备的值
        options.inTargetDensity = 160; //如果inTargetDensity != inDensity && inScaled == true,inDensity将采用inTargetDensity值，和inDensity一起使用
        options.inScaled = true; //如果 inDensity、inTargetDensity 不为0，inScale = true,将控制图片缩放到inTargetDensity 的值
//        options.inScreenDensity = 480;
//         options.inBitmap = ; //内存复用，需要新申请的图片内存小于 旧的Bitmap内存，该方法可能有其他问题 圆角图片相互影响
        Bitmap immutableBitmap = BitmapFactory.decodeFile(path, options);
//        Bitmap immutableBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_id, options);
//        eraserColor(mutableBitmap);
        iv_bitmap.setImageBitmap(immutableBitmap);
        printBitmap(immutableBitmap);
    }

    public void operation3(View v){
        String path = "/storage/emulated/0/mimixiche/compressScanPicture.jpg";
        try {
            FileInputStream in = new FileInputStream(path);
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(in.getFD());
            iv_bitmap.setImageBitmap(bitmap);
            printBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eraserColor(Bitmap bitmap) {
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true); //将Bitmap 置为 mutable
        mutableBitmap.eraseColor(Color.RED); //以特定颜色填充Bitmap像素
    }

    private void printBitmap(Bitmap bitmap) {
        if (bitmap == null) return;
        //表示适合该Bitmap的DPI，当目标屏幕的DPI不等于Bitmap的density时，就会缩放适应设备的要求。 默认density是屏幕密度
        // 例如 Bitmap density = 320, 屏幕DPI是 480，图片就会缩放 1.5倍
        int density = bitmap.getDensity();
        int byteCount = bitmap.getByteCount();
        int allocationByteCount = bitmap.getAllocationByteCount();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap.Config config = bitmap.getConfig();
        String configName = config != null ? config.name() : "";

        int rowBytes = bitmap.getRowBytes();
        int pixel = bitmap.getPixel(100, 100); //返回特定位置像素的颜色值

//        bitmap.eraseColor();
        boolean hasAlpha = bitmap.hasAlpha();
        boolean hasMipMap = bitmap.hasMipMap();
        boolean isMutable = bitmap.isMutable();
        boolean isPremultiplied = bitmap.isPremultiplied();
        float screenDensity = SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_DENSITY);
        float screenDensityDpi = SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_DENSITY_DPI);
        float screenScaleDensity = SizeUtil.getScreenParam(SizeUtil.COMMAND_SCREEN_SCALE_DENSITY);

        String bitmapInfo = " density :" + density
                + "\n width:" + width + "\n height:" + height
                + "\n rowBytes:" + rowBytes + "\n 100 pix:" + pixel
                + "\n byteCount:" + byteCount
                + "\n allocationByteCount:" + allocationByteCount
                + "\n configName:" + configName
                + "\n hasAlpha：" + hasAlpha
                + "\n hasMipMap：" + hasMipMap
                + "\n isMutable：" + isMutable
                + "\n isPremultiplied：" + isPremultiplied
                + "\n screenDensity:" + screenDensity
                + "\n screenDensityDpi:" + screenDensityDpi
                + "\n screenScaleDensity:" + screenScaleDensity;
        System.out.println("打印 ------------ " + bitmapInfo);
        tv_bitmap_info.setText(bitmapInfo);
    }
}
