package com.example.bestytb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bestytb.R;
import com.example.bestytb.pojo.PojoYoutubeVideo;

import java.util.List;

public class VideoYoutubeAdapter extends RecyclerView.Adapter<VideoYoutubeAdapter.VideoYoutubeViewHolder> {
    private List<PojoYoutubeVideo> videoList;

    public class VideoYoutubeViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDesc;

        public VideoYoutubeViewHolder(View view){
            super(view);

            tvTitle = view.findViewById(R.id.recyclerItemTitle);
            tvDesc = view.findViewById(R.id.recyclerItemDescription);
        }
    }

    public VideoYoutubeAdapter(List<PojoYoutubeVideo> videos) {
        videoList = videos;
    }


    @Override
    public VideoYoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_ytb_item,parent,false);
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
