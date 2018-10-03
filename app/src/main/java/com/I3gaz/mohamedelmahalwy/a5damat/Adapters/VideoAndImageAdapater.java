package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.VideoAndImageModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAndImageAdapater extends RecyclerView.Adapter<VideoAndImageAdapater.ViewHolder> {
    List<VideoAndImageModel> imges_and_videos_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;

    public VideoAndImageAdapater(List<VideoAndImageModel> imges_and_videos_list, Context context) {
        this.imges_and_videos_list = imges_and_videos_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.add_service_gallery_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return imges_and_videos_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.youtube_player_view)
        YouTubePlayer youtube_player_view;
        @BindView(R.id.iv_service)
        ImageView iv_service;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
