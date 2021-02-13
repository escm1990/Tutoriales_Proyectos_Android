package com.escm.lbn.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.escm.lbn.R;
import com.escm.lbn.helpers.Constants;
import com.escm.lbn.youtube.VideoYT;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<VideoYT> videoList;

    public YoutubeAdapter(Context context, List<VideoYT> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    class YoutubeHolder extends RecyclerView.ViewHolder{

        ImageView miniatura;
        TextView titulo, detalle;

        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            miniatura = itemView.findViewById(R.id.img_miniatura_youtube);
            titulo = itemView.findViewById(R.id.tv_titulo_youtube);
            detalle = itemView.findViewById(R.id.tv_detalle_youtube);
        }

        public void setData(VideoYT videoYT) {
            String getTitulo = videoYT.getSnippet().getTitle();
            String getFecha = videoYT.getSnippet().getDescription();
            String getMiniatura = videoYT.getSnippet().getThumbnails().getMedium().getUrl();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(Constants.url_youtube_watch+videoYT.getId().getVideoId());
                    Log.v("TAG", "Link al video "+Constants.url_youtube_watch+videoYT.getId().getVideoId());
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    context.startActivity(intent);
                }
            });

            titulo.setText(getTitulo);
            detalle.setText(getFecha);
            Picasso.get().load(getMiniatura).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(miniatura, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("TAG", "Miniatura Success");
                }

                @Override
                public void onError(Exception e) {
                    Log.e("TAG", "Miniatura Error: ", e);
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item_youtube, parent,false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VideoYT videoYT = videoList.get(position);
        YoutubeHolder yth = (YoutubeHolder) holder;
        yth.setData(videoYT);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
