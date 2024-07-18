package com.example.bestytb.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PojoYoutubeVideo {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String titre;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private String categorie;

    @ColumnInfo
    private int favori;

    public PojoYoutubeVideo(String titre, String description, String url, String categorie, int favori) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
        this.favori = favori;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }
}
