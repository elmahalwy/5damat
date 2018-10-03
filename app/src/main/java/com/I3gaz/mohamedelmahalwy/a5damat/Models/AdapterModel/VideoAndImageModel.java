package com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel;

import android.net.Uri;

public class VideoAndImageModel {
    String youtube_player_view,iv_service,type;
    Uri image_uri;

    public String getYoutube_player_view() {
        return youtube_player_view;
    }

    public void setYoutube_player_view(String youtube_player_view) {
        this.youtube_player_view = youtube_player_view;
    }

    public String getIv_service() {
        return iv_service;
    }

    public void setIv_service(String iv_service) {
        this.iv_service = iv_service;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Uri getImage_uri() {
        return image_uri;
    }

    public void setImage_uri(Uri image_uri) {
        this.image_uri = image_uri;
    }
}
