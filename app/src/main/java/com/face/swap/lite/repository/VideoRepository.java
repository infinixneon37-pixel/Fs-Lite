package com.face.swap.lite.repository;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import com.face.swap.lite.model.VideoInfo;
import java.util.ArrayList;
import java.util.List;

public class VideoRepository {

    public static List<VideoInfo> getAllVideos(Context context) {
        List<VideoInfo> videoList = new ArrayList<>();
        String[] projection = {
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT
        };

        // Query video dari storage lokal, diurutkan dari yang terbaru
        try (Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, MediaStore.Video.Media.DATE_ADDED + " DESC")) {
            
            if (cursor != null) {
                int pathCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                int durCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
                int wCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH);
                int hCol = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT);

                while (cursor.moveToNext()) {
                    videoList.add(new VideoInfo(
                        cursor.getString(pathCol),
                        cursor.getLong(durCol),
                        cursor.getInt(wCol),
                        cursor.getInt(hCol)
                    ));
                }
            }
        }
        return videoList;
    }
}
