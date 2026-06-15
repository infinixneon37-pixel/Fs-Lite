package com.face.swap.lite.player;

import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

public class VideoPlayerManager {
    private ExoPlayer player;

    public VideoPlayerManager(Context context, PlayerView playerView) {
        player = new ExoPlayer.Builder(context).build();
        playerView.setPlayer(player);
    }

    public void playVideo(String path) {
        MediaItem mediaItem = MediaItem.fromUri(path);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
