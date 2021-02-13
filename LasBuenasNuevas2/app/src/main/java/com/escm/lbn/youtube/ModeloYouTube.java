package com.escm.lbn.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModeloYouTube {
    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;

    @SerializedName("items")
    @Expose
    private List<VideoYT> items;

    public ModeloYouTube() {
    }

    public ModeloYouTube(String nextPageToken, List<VideoYT> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<VideoYT> getItems() {
        return items;
    }

    public void setItems(List<VideoYT> items) {
        this.items = items;
    }
}
