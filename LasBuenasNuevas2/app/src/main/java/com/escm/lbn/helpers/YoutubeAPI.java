package com.escm.lbn.helpers;

import com.escm.lbn.youtube.ModeloYouTube;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {

    public interface YoutubeVideo{
        @GET
        Call<ModeloYouTube> getYT(@Url String url);
    }

    private static YoutubeVideo youtubeVideo = null;

    public static YoutubeVideo getYoutubeVideo(){
        if(youtubeVideo == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_YOUTUBE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            youtubeVideo = retrofit.create(YoutubeVideo.class);
        }
        return youtubeVideo;
    }
}
