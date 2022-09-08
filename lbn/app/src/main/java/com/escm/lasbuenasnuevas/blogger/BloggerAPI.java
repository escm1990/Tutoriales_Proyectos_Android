package com.escm.lbn.blogger;

import com.escm.lbn.BuildConfig;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class BloggerAPI {

    public static final String key = BuildConfig.BLOGGER_KEY;
    public static final String url = BuildConfig.BLOGGER_URL;
    public static final String urlFiltro = BuildConfig.BLOGGER_FILTRO;

    public static PostService postService =  null;

    public static PostService getService(){
        if(postService == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }

    public interface PostService
    {
        //@GET("?key="+key)
        @GET
        Call<PostList> getPostLIst(@Url String url);

        @GET("{postId}/?key="+key)
        Call<Item> getPostById(@Path("postId") String id);

    }
}
