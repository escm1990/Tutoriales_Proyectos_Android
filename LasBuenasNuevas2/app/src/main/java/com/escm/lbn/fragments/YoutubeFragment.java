package com.escm.lbn.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.escm.lbn.R;
import com.escm.lbn.adapter.YoutubeAdapter;
import com.escm.lbn.helpers.Constants;
import com.escm.lbn.helpers.YoutubeAPI;
import com.escm.lbn.youtube.ModeloYouTube;
import com.escm.lbn.youtube.VideoYT;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class YoutubeFragment extends Fragment {

    private YoutubeAdapter adapter;
    private LinearLayoutManager manager;
    public List<VideoYT> videoList = new ArrayList<>();


    public YoutubeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        // Inflate the layout for this fragment
        RecyclerView rv = view.findViewById(R.id.recyclerYoutube);
        adapter = new YoutubeAdapter(getContext(),videoList);
        manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

        if(videoList.size() == 0){
            getJson();
        }

        return view;
    }

    private void getJson() {
        String url = Constants.BASE_URL_YOUTUBE
                + Constants.SEARCH_YOUTUBE
                + Constants.API_KEY_YOUTUBE
                + Constants.ID_CHANNEL_YOUTUBE
                + Constants.MAX_RESULTS_YOUTUBE
                + Constants.ORDER_YOUTUBE
                + Constants.PART_YOUTUBE;
        Log.v("TAG","URL: "+url);
        Call<ModeloYouTube> data = YoutubeAPI.getYoutubeVideo().getYT(url);
        data.enqueue(new Callback<ModeloYouTube>() {
            @Override
            public void onResponse(Call<ModeloYouTube> call, Response<ModeloYouTube> response) {
                Log.v("TAG","response.body() --> "+response.body());
                if (response.errorBody() != null){
                    Log.w("TAG","Error Modelo Youtube: "+response.errorBody());
                } else {
                    ModeloYouTube mY = response.body();
                    videoList.addAll(mY.getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ModeloYouTube> call, Throwable t) {
                Log.e("TAG","onFailure Modelo Youtube: ",t);
            }
        });
    }
}