package com.example.bestytb.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bestytb.pojo.PojoYoutubeVideo;

import java.util.List;

@Dao
public interface VideoYoutubeDAO {

    @Query("Select * from pojoyoutubevideo where id =:id")
    public PojoYoutubeVideo find(Long id);

    @Query("Select * from pojoyoutubevideo")
    public List<PojoYoutubeVideo> list();

    @Query("Select * from pojoyoutubevideo where favori > 0")
    public List<PojoYoutubeVideo> listFav();

    @Insert
    public void add(PojoYoutubeVideo... pojoYoutubeVideos);

    @Update
    public void update(PojoYoutubeVideo... pojoYoutubeVideos);

    @Delete
    public void delete(PojoYoutubeVideo... pojoYoutubeVideos);


}
