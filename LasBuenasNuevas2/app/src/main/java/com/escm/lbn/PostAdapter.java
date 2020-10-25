package com.escm.lbn;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Item> items;


    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Item item = items.get(position);
        holder.postTitle.setText(item.getTitle());
        /*
        Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
        Matcher m = p.matcher(item.getContent());
        List<String> tokens = new ArrayList<>();
        while(m.find()){
            String token = m.group(1);
            tokens.add(token);
        }
        Glide.with(context).load(tokens.get(0)).into(holder.postImage);
        */

        //parseando el texto del post
        Document document = Jsoup.parse(item.getContent());
        holder.postDescription.setText(document.text());

        //obteniendo la imagen del contenido del item (post) recuperado
        try {
            Elements elements = document.select("img");
            Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);//esto obtendrá la PRIMERA IMAGEN que encuentre en el contenido
        }catch (Exception ex){
            Glide.with(context).load(Constants.imgDefault).into(holder.postImage);
            ex.printStackTrace(System.err);
        }

        //Mostrando el detalle del post al dar clic sobre el
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PostDetailActivity.class);
                intent.putExtra("url",item.getUrl());
                intent.putExtra("title",item.getTitle());
                intent.putExtra("content",item.getContent());
                intent.putExtra("idPost",item.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        ImageView postImage;
        TextView postTitle;
        TextView postDescription;

        public PostViewHolder(View itemView){
            super(itemView);
            postImage = (ImageView) itemView.findViewById(R.id.postImage);
            postTitle = (TextView) itemView.findViewById(R.id.postTitle);
            postDescription = (TextView) itemView.findViewById(R.id.postDescription);
        }
    }

}
