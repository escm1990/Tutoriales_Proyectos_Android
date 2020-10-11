package com.escm.lbn;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class BloggerAPI {

    public static final String key = "AIzaSyDYQn7qe0Kf4_pwbCI44XJLGmH5aAGJzyM";
    public static final String url = "https://www.googleapis.com/blogger/v3/blogs/6735840350459906273/posts/";

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
/*
        @GET("{postId}/?key="+key)
        Call<Item> getPostById(@Path("postId") String id);
*/
    }
}
