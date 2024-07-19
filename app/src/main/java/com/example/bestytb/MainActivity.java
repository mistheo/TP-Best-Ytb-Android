package com.example.bestytb;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bestytb.adapters.VideoYoutubeAdapter;
import com.example.bestytb.databases.VideoYoutubeDatabase;
import com.example.bestytb.pojo.PojoYoutubeVideo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyViewVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Show Toolbar
        Toolbar toolbarMainMenu = findViewById(R.id.toolbarMainMenu);
        setSupportActionBar(toolbarMainMenu);

        //RecyclerView
        recyViewVideo = findViewById(R.id.recyclerViewVideoYoutube);
        RecyclerView.LayoutManager lytMnger = new LinearLayoutManager(getApplicationContext());
        recyViewVideo.setHasFixedSize(true);
        recyViewVideo.setLayoutManager(lytMnger);

    }


    @Override
    protected void onStart() {
        super.onStart();
        //Get Db videos
        List<PojoYoutubeVideo> videos = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().list();

        //Prends les infos de la video et les affiches sur le recyclerview avec l'adaptateur crée en amont
        VideoYoutubeAdapter videoYoutubeAdapter = new VideoYoutubeAdapter(videos);
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
            startActivity(new Intent(getApplicationContext(),ActivityAddaYoutubeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}