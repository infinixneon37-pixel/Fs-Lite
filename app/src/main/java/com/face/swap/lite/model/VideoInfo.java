package com.face.swap.lite.model;

public class VideoInfo {
    public String path;
    public long durationMs;
    public int width;
    public int height;

    public VideoInfo(String path, long durationMs, int width, int height) {
        this.path = path;
        this.durationMs = durationMs;
        this.width = width;
        this.height = height;
    }
}
