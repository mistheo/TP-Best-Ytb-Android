package com.example.bestytb.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.example.bestytb.dao.VideoYoutubeDAO;
import com.example.bestytb.pojo.PojoYoutubeVideo;

@Database(entities = {PojoYoutubeVideo.class}, version = 1)
public abstract class VideoYoutubeDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "videoYoutube";

    public static VideoYoutubeDatabase getDb(Context ctx) {
        return Room.databaseBuilder(ctx,VideoYoutubeDatabase.class,DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public abstract VideoYoutubeDAO videoYoutubeDAO();
}
