package com.example.bestytb.adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bestytb.DetailVideoActivity;
import com.example.bestytb.R;
import com.example.bestytb.pojo.PojoYoutubeVideo;

import java.util.List;

public class VideoYoutubeAdapter extends RecyclerView.Adapter<VideoYoutubeAdapter.VideoYoutubeViewHolder> {
    private List<PojoYoutubeVideo> videoList;

    public class VideoYoutubeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle;
        public TextView tvDesc;
        Context ctx;

        public VideoYoutubeViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.recyclerItemTitle);
            tvDesc = view.findViewById(R.id.recyclerItemDescription);
            ctx = view.getContext();
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition();
            PojoYoutubeVideo video = videoList.get(position);

            Intent intent = new Intent(ctx, DetailVideoActivity.class);
            intent.putExtra("title", video.getTitre());
            intent.putExtra("description", video.getDescription());
            intent.putExtra("url", video.getUrl());
            intent.putExtra("categorie", video.getCategorie());
            intent.putExtra("favori",video.getFavori());
            intent.putExtra("id",video.getId());
            ctx.startActivity(intent);
        }
    }

    public VideoYoutubeAdapter(List<PojoYoutubeVideo> videos) {
        videoList = videos;
    }

    @Override
    public VideoYoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_ytb_item, parent, false);
        return new VideoYoutubeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VideoYoutubeViewHolder holder, int position) {
        PojoYoutubeVideo video = videoList.get(position);
        holder.tvTitle.setText(video.getTitre());
        holder.tvDesc.setText(video.getDescription());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
