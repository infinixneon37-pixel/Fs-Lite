package com.face.swap.lite.utils;

import android.graphics.Bitmap;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class BitmapUtils {

    public static Mat bitmapToMat(Bitmap bmp) {
        Mat mat = new Mat();
        Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);
        
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGBA2BGR);
        return mat;
    }

    public static Bitmap matToBitmap(Mat mat) {
        Mat tmp = new Mat();
        if (mat.channels() == 3) {
            Imgproc.cvtColor(mat, tmp, Imgproc.COLOR_BGR2RGBA);
        } else {
            mat.copyTo(tmp);
        }
        
        Bitmap bmp = Bitmap.createBitmap(tmp.cols(), tmp.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(tmp, bmp);
        tmp.release();
        return bmp;
    }
}
