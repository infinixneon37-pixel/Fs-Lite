package com.face.swap.lite.ai;

import android.content.Context;
import android.util.Log;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import com.face.swap.lite.utils.FileUtils;

public class ONNXManager {
    private static final String TAG = "ONNXManager";
    
    private OrtEnvironment env;
    private OrtSession session;

    public ONNXManager(Context context, String modelFileName) {
        try {
            String modelPath = FileUtils.getModelFilePath(context, modelFileName);
            env = OrtEnvironment.getEnvironment();
            
            OrtSession.SessionOptions options = new OrtSession.SessionOptions();
            options.setOptimizationLevel(OrtSession.SessionOptions.OptLevel.BASIC_OPT);
            
            session = env.createSession(modelPath, options);
            Log.d(TAG, "ONNX Session loaded successfully for: " + modelFileName);
            
        } catch (Exception e) {
            Log.e(TAG, "Error initializing ONNX Runtime: ", e);
        }
    }

    public OrtEnvironment getEnv() {
        return env;
    }

    public OrtSession getSession() {
        return session;
    }

    public void close() {
        try {
            if (session != null) {
                session.close();
            }
            if (env != null) {
                env.close();
            }
        } catch (OrtException e) {
            Log.e(TAG, "Error closing ONNX Runtime: ", e);
        }
    }
}
