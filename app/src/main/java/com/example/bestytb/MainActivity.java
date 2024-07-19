package com.example.bestytb;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestytb.adapters.VideoYoutubeAdapter;
import com.example.bestytb.databases.VideoYoutubeDatabase;
import com.example.bestytb.pojo.PojoYoutubeVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyViewVideo;
    TextView txtNoFav;
    SwitchCompat swicthFav;
    VideoYoutubeAdapter videoYoutubeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //no fav
        txtNoFav = findViewById(R.id.textNoFav);

        //Show Toolbar
        Toolbar toolbarMainMenu = findViewById(R.id.toolbarMainMenu);
        setSupportActionBar(toolbarMainMenu);

        //RecyclerView
        recyViewVideo = findViewById(R.id.recyclerViewVideoYoutube);
        RecyclerView.LayoutManager lytMnger = new LinearLayoutManager(getApplicationContext());
        recyViewVideo.setHasFixedSize(true);
        recyViewVideo.setLayoutManager(lytMnger);

        //switch
        swicthFav = findViewById(R.id.switchToggleFav);
        swicthFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swicthFav.isChecked()) {
                    swicthFav.setText("On");
                    setTitle("My Best Youtube - Favoris");
                    List<PojoYoutubeVideo> vfav = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().listFav();
                    if (vfav.isEmpty()) {
                        txtNoFav.setVisibility(View.VISIBLE);
                    }
                    else {
                        txtNoFav.setVisibility(View.INVISIBLE);
                    }

                    videoYoutubeAdapter = new VideoYoutubeAdapter(vfav);
                    recyViewVideo.setAdapter(videoYoutubeAdapter);

                } else {
                    swicthFav.setText("Off");
                    setTitle("My Best Youtube");
                    List<PojoYoutubeVideo> vall = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().list();
                    videoYoutubeAdapter = new VideoYoutubeAdapter(vall);
                    recyViewVideo.setAdapter(videoYoutubeAdapter);
                }
                videoYoutubeAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get Db videos
        List<PojoYoutubeVideo> videos = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().list();

        //Prends les infos de la video et les affiches sur le recyclerview avec l'adaptateur crée en amount
        videoYoutubeAdapter = new VideoYoutubeAdapter(videos);
        recyViewVideo.setAdapter(videoYoutubeAdapter);
    }

    //Manage Toolbar Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.ItemMenuAddVideo) {
            Intent i = new Intent(getApplicationContext(), AddYoutubeVideoActivity.class);
            i.putExtra("mode",0);
            startActivity(i);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}