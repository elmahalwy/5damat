package com.I3gaz.mohamedelmahalwy.a5damat.Models.AdapterModel;

import android.net.Uri;

public class VideoAndImageModel {
    String youtube_player_view,iv_service,type,image_for_edit;
    Uri image_uri;
    int file_size;

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

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getImage_for_edit() {
        return image_for_edit;
    }

    public void setImage_for_edit(String image_for_edit) {
        this.image_for_edit = image_for_edit;
    }
}
