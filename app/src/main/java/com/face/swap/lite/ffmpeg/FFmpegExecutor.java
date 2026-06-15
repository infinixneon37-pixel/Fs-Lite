package com.face.swap.lite.ffmpeg;

import android.util.Log;
import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.ReturnCode;

public class FFmpegExecutor {
    private static final String TAG = "FFmpegExecutor";

    public interface FFmpegCallback {
        void onSuccess();
        void onFailure(String errorOutput);
    }

    public static void executeCommand(String command, FFmpegCallback callback) {
        Log.d(TAG, "Executing: " + command);
        
        FFmpegKit.executeAsync(command, session -> {
            ReturnCode returnCode = session.getReturnCode();
            
            if (ReturnCode.isSuccess(returnCode)) {
                Log.d(TAG, "FFmpeg process completed successfully.");
                callback.onSuccess();
            } else if (ReturnCode.isCancel(returnCode)) {
                Log.d(TAG, "FFmpeg process cancelled.");
            } else {
                String failMsg = session.getFailStackTrace();
                Log.e(TAG, "FFmpeg process failed: " + failMsg);
                callback.onFailure(failMsg);
            }
        }, log -> {
            // Log.d(TAG, log.getMessage());
        }, statistics -> {
        });
    }
}
