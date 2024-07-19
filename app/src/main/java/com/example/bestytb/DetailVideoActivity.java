package com.example.bestytb;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bestytb.dao.VideoYoutubeDAO;
import com.example.bestytb.databases.VideoYoutubeDatabase;
import com.example.bestytb.pojo.PojoYoutubeVideo;


public class DetailVideoActivity extends AppCompatActivity {

    Button btnPlayVideo;
    TextView tvTitle;
    TextView tvDesc;
    TextView tvLink;
    TextView tvCategorie;
    PojoYoutubeVideo vYtb;

    String title;
    String description;
    String url;
    String categorie;
    int favori;
    long id;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        btnPlayVideo = findViewById(R.id.buttonPlayVideo);

        // Toolbar
        Toolbar toolbarDetailMenu = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbarDetailMenu);

        Log.d("Detail","DANS DETAIL ACTIVITY");

        tvTitle = findViewById(R.id.detailVideoTitle);
        tvDesc = findViewById(R.id.detailVideoDescription);
        tvLink = findViewById(R.id.detailVideoLink);
        tvCategorie = findViewById(R.id.detailVideoCategorie);

        btnPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                );
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // intent
        id = getIntent().getLongExtra("id",0);
        vYtb = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().find(id);

        // view
        tvTitle.setText("Titre: " + vYtb.getTitre());
        tvDesc.setText("Description: " + vYtb.getDescription());
        tvLink.setText("Liens: https://www.youtube.com/watch?v=" + vYtb.getUrl());
        tvCategorie.setText("Categorie: " + vYtb.getCategorie());
        this.setTitle("Detail de "+vYtb.getTitre());
    }

    //Manage Toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if( vYtb.getFavori() == 0) {
            menu.findItem(R.id.ItemMenuDelFavori).setVisible(false);
            menu.findItem(R.id.ItemMenuAddFavori).setVisible(true);
        }
        else {
            menu.findItem(R.id.ItemMenuDelFavori).setVisible(true);
            menu.findItem(R.id.ItemMenuAddFavori).setVisible(false);
        }
        return super.onMenuOpened(featureId, menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.ItemMenuAddFavori) {
            vYtb.setFavori(1);
            VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().update(vYtb);
        }
        if (item.getItemId() == R.id.ItemMenuDelFavori) {
            vYtb.setFavori(0);
            VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().update(vYtb);
        }
        if(item.getItemId() == R.id.ItemMenuBack)
            finish();
        if(item.getItemId() == R.id.ItemMenuDelete) {
            VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().delete(vYtb);
            finish();
        }
        if(item.getItemId() == R.id.ItemMenuModify) {
            Intent intent = new Intent(getApplicationContext(), AddYoutubeVideoActivity.class);
            intent.putExtra("id",vYtb.getId());
            intent.putExtra("mode",1);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}