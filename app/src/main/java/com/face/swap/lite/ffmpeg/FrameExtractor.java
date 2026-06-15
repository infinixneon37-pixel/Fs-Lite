package com.face.swap.lite.ffmpeg;

import java.io.File;

public class FrameExtractor {
    
    public static void extractFrames(String videoPath, String outputDirPath, FFmpegExecutor.FFmpegCallback callback) {
        File outDir = new File(outputDirPath);
        if (!outDir.exists()) outDir.mkdirs();

        // Ekstrak frame video menjadi urutan gambar PNG untuk menjaga kualitas (lossless) sebelum di-swap
        String command = String.format("-y -i \"%s\" -q:v 2 \"%s/frame_%%05d.png\"", videoPath, outputDirPath);
        FFmpegExecutor.executeCommand(command, callback);
    }
    
    public static void extractAudio(String videoPath, String outputAudioPath, FFmpegExecutor.FFmpegCallback callback) {
        // Ekstrak murni audionya saja tanpa re-encode
        String command = String.format("-y -i \"%s\" -vn -acodec copy \"%s\"", videoPath, outputAudioPath);
        FFmpegExecutor.executeCommand(command, callback);
    }
}
