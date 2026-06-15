package com.face.swap.lite.model;

import org.opencv.core.Point;
import org.opencv.core.Rect;

public class FaceInfo {
    public Rect boundingBox;
    public Point[] landmarks; // Menyimpan 5 titik referensi mata, hidung, mulut
    public float[] embedding; // Vektor fitur wajah dari model recognition (biasanya 512 dimensi)

    public FaceInfo(Rect boundingBox, Point[] landmarks, float[] embedding) {
        this.boundingBox = boundingBox;
        this.landmarks = landmarks;
        this.embedding = embedding;
    }
}
