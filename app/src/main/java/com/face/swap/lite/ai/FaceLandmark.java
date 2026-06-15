package com.face.swap.lite.ai;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FaceLandmark {
    
    private static final Point[] REFERENCE_POINTS_128 = {
        new Point(38.41, 51.72),
        new Point(89.59, 51.72),
        new Point(64.0, 78.41),
        new Point(42.56, 100.95),
        new Point(85.44, 100.95)
    };

    public static Mat getAffineMatrix(Point[] sourceLandmarks) {
        MatOfPoint2f srcPoints = new MatOfPoint2f(sourceLandmarks);
        MatOfPoint2f dstPoints = new MatOfPoint2f(REFERENCE_POINTS_128);
        return Imgproc.estimateAffinePartial2D(srcPoints, dstPoints);
    }

    public static Mat alignFace(Mat originalFrame, Mat affineMatrix, int targetSize) {
        Mat alignedFace = new Mat();
        Size size = new Size(targetSize, targetSize);
        Imgproc.warpAffine(originalFrame, alignedFace, affineMatrix, size, Imgproc.INTER_CUBIC);
        return alignedFace;
    }

    public static Mat pasteFaceBack(Mat originalFrame, Mat swappedFace, Mat affineMatrix) {
        Mat inverseMatrix = new Mat();
        Imgproc.invertAffineTransform(affineMatrix, inverseMatrix);

        Mat restoredFace = new Mat();
        Size originalSize = new Size(originalFrame.cols(), originalFrame.rows());
        Imgproc.warpAffine(swappedFace, restoredFace, inverseMatrix, originalSize, Imgproc.INTER_CUBIC);
        
        return restoredFace; 
    }
}
