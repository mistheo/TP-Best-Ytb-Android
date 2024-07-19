package com.example.bestytb;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class DetailVideoActivity extends AppCompatActivity {

    Button btnPlayVideo;
    @Override
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        btnPlayVideo = findViewById(R.id.buttonPlayVideo);

        Log.d("Detail","DANS DETAIL ACTIVITY");

        TextView tvTitle = findViewById(R.id.detailVideoTitle);
        TextView tvDesc = findViewById(R.id.detailVideoDescription);
        TextView tvLink = findViewById(R.id.detailVideoLink);
        TextView tvCategorie = findViewById(R.id.detailVideoCategorie);

        // Get data from intent
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String url = "https://www.youtube.com/watch?v=" + getIntent().getStringExtra("url");
        String categorie = getIntent().getStringExtra("categorie");

        // Set data to views
        tvTitle.setText(title);
        tvDesc.setText(description);
        tvLink.setText(url);
        tvCategorie.setText(categorie);


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
}