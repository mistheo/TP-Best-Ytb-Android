package com.example.bestytb;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bestytb.databases.VideoYoutubeDatabase;
import com.example.bestytb.pojo.PojoYoutubeVideo;
import com.google.android.material.textfield.TextInputEditText;

public class AddYoutubeVideoActivity extends AppCompatActivity {
    Button btnCancel;
    Button btnSave;
    TextInputEditText inputTitle;
    TextInputEditText inputDesc;
    TextInputEditText inputLink;
    Spinner spinCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);

        //Build item
        btnCancel = findViewById(R.id.buttonCancel);
        btnSave = findViewById(R.id.buttonAdd);
        inputDesc = findViewById(R.id.inputDescription);
        inputLink = findViewById(R.id.inputYtbURL);
        inputTitle = findViewById(R.id.inputTitre);
        spinCat = findViewById(R.id.spinnerCategorie);
        int state = getIntent().getIntExtra("mode",0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[] {
                        "Humour", "Musique", "Jeux Video", "Sport", "Actu", "Culture" , "Mode & Beaute" , "Podcasts" , "Autre" });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCat.setAdapter(adapter);

        if (state == 1) {
            inputDesc.setText(getIntent().getStringExtra("description"));
            inputLink.setText(getIntent().getStringExtra("url"));
            inputTitle.setText(getIntent().getStringExtra("title"));

            int spinnerPosition = adapter.getPosition(getIntent().getStringExtra("categorie"));
            spinCat.setSelection(spinnerPosition);
            btnSave.setText("MODIFIER");

        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String videoName = inputTitle.getText().toString();
                String videoDesc = inputDesc.getText().toString();
                String videoLink = extractYouTubeID(inputLink.getText().toString());
                String videoCat = spinCat.getSelectedItem().toString();

                if (videoName.isEmpty() || videoLink.isEmpty() || videoCat.isEmpty() || videoDesc.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Certain elements semble invalide...",Toast.LENGTH_LONG);
                } else {
                    PojoYoutubeVideo vYtb = new PojoYoutubeVideo(
                            videoName,
                            videoDesc,
                            videoLink,
                            videoCat,
                            0
                    );

                    if (state == 0) {
                        VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().add(vYtb);
                    }
                    else {
                        PojoYoutubeVideo updatedVideo = VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().find(getIntent().getLongExtra("id",0));
                        updatedVideo.setTitre(videoName);
                        updatedVideo.setDescription(videoDesc);
                        updatedVideo.setUrl(videoLink);
                        updatedVideo.setCategorie(videoCat);
                        VideoYoutubeDatabase.getDb(getApplicationContext()).videoYoutubeDAO().update(updatedVideo);
                    }
                    finish();
                }
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static String extractYouTubeID(String url) {
        // Regex pour les différentes variations des liens YouTube
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?v%3D|watch\\?vi=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|\\/v%2F|\\/e%2F|youtu.be\\/|\\/v\\/|\\/e\\/|\\?v=|\\&v=|youtu.be\\/|\\/embed\\/|\\/watch\\/|\\?vi=|\\&vi=|youtu.be%3Fv=|\\?v%3D|\\&v%3D|youtu.be\\?|\\?vi%3D|\\&vi%3D)[a-zA-Z0-9_-]{11}";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);

        if (matcher.find()) {
            return matcher.group();
        } else {
            return url; // Retourne l'URL d'origine si l'ID n'est pas trouvé (supposant que c'est déjà l'ID)
        }
    }
}