package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.ServiceDetails.ImagesVIdeosModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceDetailsVideosAndImagesAdapter extends RecyclerView.Adapter<ServiceDetailsVideosAndImagesAdapter.ViewHolder> {
    List<ImagesVIdeosModel> imges_and_videos_list= new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    Bitmap bitmap;


    public ServiceDetailsVideosAndImagesAdapter(List<ImagesVIdeosModel> imges_and_videos_list,Context context) {
        this.imges_and_videos_list = imges_and_videos_list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.service_details_item_for_videos_and_images, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (imges_and_videos_list.get(position).getType().equals("video")) {
            holder.iv_service.setVisibility(View.GONE);
            holder.relative_for_video.setVisibility(View.VISIBLE);
            ((HomeActivity) context).getLifecycle().addObserver(holder.youtubePlayerView);
            holder.youtubePlayerView.initialize(new YouTubePlayerInitListener() {
                @Override
                public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                    initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            String videoId = imges_and_videos_list.get(position).getVideo_image();
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        }
                    });
                }
            }, true);
        }
        if (imges_and_videos_list.get(position).getType().equals("image")){
            holder.iv_service.setVisibility(View.VISIBLE);
            holder.relative_for_video.setVisibility(View.GONE);
            Picasso.with(context)
                    .load(imges_and_videos_list.get(position).getVideo_image())
                    .into(holder.iv_service);
        }
    }

    @Override
    public int getItemCount() {
        return imges_and_videos_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.youtube_player_view)
        YouTubePlayerView youtubePlayerView;
        @BindView(R.id.iv_service)
        ImageView iv_service;
        @BindView(R.id.relative_for_video)
        RelativeLayout relative_for_video;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
