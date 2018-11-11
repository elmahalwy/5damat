package com.I3gaz.mohamedelmahalwy.a5damat.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.I3gaz.mohamedelmahalwy.a5damat.Activites.HomeActivity;
import com.I3gaz.mohamedelmahalwy.a5damat.Fragments.AddServiceFragment;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.SubCatigoriesModel;
import com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel.VideoAndImageModel;
import com.I3gaz.mohamedelmahalwy.a5damat.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoAndImageAdapater extends RecyclerView.Adapter<VideoAndImageAdapater.ViewHolder> {
    List<VideoAndImageModel> imges_and_videos_list = new ArrayList<>();
    Context context;
    LayoutInflater layoutInflater;
    int lastPosition = -1;
    Bitmap bitmap;


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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (imges_and_videos_list.size() < 2) {
            holder.iv_left.setVisibility(View.GONE);
            holder.iv_right.setVisibility(View.GONE);
        } else {
            holder.iv_left.setVisibility(View.VISIBLE);
            holder.iv_right.setVisibility(View.VISIBLE);
        }
        if (imges_and_videos_list.get(position).getType().equals("file")) {
            if ((((HomeActivity) context).came_from.equals("edit"))) {
                holder.iv_service.setVisibility(View.VISIBLE);
                holder.relative_for_video.setVisibility(View.GONE);
                Picasso.with(context).load(imges_and_videos_list.get(position).getImage_for_edit()).into(holder.iv_service);
            }
            if ((((HomeActivity) context).came_from.equals("add"))) {
                holder.iv_service.setVisibility(View.VISIBLE);
                holder.relative_for_video.setVisibility(View.GONE);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imges_and_videos_list.get(position).getImage_uri());
                    holder.iv_service.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (imges_and_videos_list.get(position).getType().equals("video_link")) {
            holder.iv_service.setVisibility(View.GONE);
            holder.relative_for_video.setVisibility(View.VISIBLE);
            ((HomeActivity) context).getLifecycle().addObserver(holder.youtubePlayerView);
            holder.youtubePlayerView.initialize(new YouTubePlayerInitListener() {
                @Override
                public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                    initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            String videoId = imges_and_videos_list.get(position).getYoutube_player_view();
                            initializedYouTubePlayer.loadVideo(videoId, 0);
                        }
                    });
                }
            }, true);
        }
        if (imges_and_videos_list.get(position).getType().equals("image_link")) {
            holder.iv_service.setVisibility(View.VISIBLE);
            holder.relative_for_video.setVisibility(View.GONE);
            Picasso.with(context).load(imges_and_videos_list.get(position).getIv_service()).into(holder.iv_service);
        }
        holder.iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    AddServiceFragment.rv_videos_and_images.scrollToPosition(imges_and_videos_list.size() - 1);
                } else {
                    AddServiceFragment.rv_videos_and_images.scrollToPosition(position - 1);

                }
            }
        });
        holder.iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == imges_and_videos_list.size() - 1) {
                    AddServiceFragment.rv_videos_and_images.scrollToPosition(0);

                } else {
                    AddServiceFragment.rv_videos_and_images.scrollToPosition(position + 1);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return imges_and_videos_list.size();
    }

    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.youtube_player_view)
        YouTubePlayerView youtubePlayerView;
        @BindView(R.id.iv_service)
        ImageView iv_service;
        @BindView(R.id.relative_for_video)
        RelativeLayout relative_for_video;
        @BindView(R.id.iv_left)
        ImageView iv_left;
        @BindView(R.id.iv_right)
        ImageView iv_right;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
