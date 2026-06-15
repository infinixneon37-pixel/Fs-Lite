package com.face.swap.lite.ffmpeg;

public class VideoEncoder {

    public static void encodeVideo(String framesDirPath, String audioPath, String outputVideoPath, int fps, FFmpegExecutor.FFmpegCallback callback) {
        // Menggabungkan kembali frame yang sudah diedit dengan audio asli ke format MP4 H.264
        String command = String.format("-y -framerate %d -i \"%s/frame_%%05d.png\" -i \"%s\" -c:v libx264 -pix_fmt yuv420p -c:a aac -strict experimental \"%s\"", 
                fps, framesDirPath, audioPath, outputVideoPath);
        FFmpegExecutor.executeCommand(command, callback);
    }
}
