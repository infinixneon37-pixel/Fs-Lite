package com.face.swap.lite;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.exoplayer.ExoPlayer;
import com.face.swap.lite.databinding.ActivityMainBinding;
import com.face.swap.lite.utils.PermissionUtils;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1. Inisialisasi OpenCV
        if (OpenCVLoader.initLocal()) {
            Log.i("OpenCV", "OpenCV loaded successfully");
        } else {
            Log.e("OpenCV", "OpenCV initialization failed!");
        }

        // 2. Cek Perizinan Storage Android 13+
        if (!PermissionUtils.hasStoragePermissions(this)) {
            PermissionUtils.requestPermissions(this);
        }

        // 3. Setup Player & UI
        initializePlayer();
        setupListeners();
    }

    private void initializePlayer() {
        player = new ExoPlayer.Builder(this).build();
        binding.playerView.setPlayer(player);
    }

    private void setupListeners() {
        binding.btnSelectVideo.setOnClickListener(v -> {
            Toast.makeText(this, "Pilih video...", Toast.LENGTH_SHORT).show();
        });

        binding.btnProcess.setOnClickListener(v -> {
            Toast.makeText(this, "Memulai proses...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }
}
