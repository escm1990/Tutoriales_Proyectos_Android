package com.escm.lbn.youtube;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoYT {
    @SerializedName("id")
    @Expose
    private VideoId id;

    @SerializedName("snippet")
    @Expose
    private SnippetYT snippet;

    public VideoYT() {
    }

    public VideoYT(VideoId id, SnippetYT snippet) {
        this.id = id;
        this.snippet = snippet;
    }

    public VideoId getId() {
        return id;
    }

    public void setId(VideoId id) {
        this.id = id;
    }

    public SnippetYT getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetYT snippet) {
        this.snippet = snippet;
    }
}
