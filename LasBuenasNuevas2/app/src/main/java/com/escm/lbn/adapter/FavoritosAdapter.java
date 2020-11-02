package com.escm.lbn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.escm.lbn.entities.Favorito;
import com.escm.lbn.R;

import java.util.ArrayList;

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> implements View.OnClickListener {

    ArrayList<Favorito> listaFavoritos;
    private View.OnClickListener listener;

    public FavoritosAdapter(ArrayList<Favorito> listaFavoritos){
        this.listaFavoritos = listaFavoritos;
    }

    public FavoritosAdapter(Context context, ArrayList<Favorito> listaFavoritos){
        this.listaFavoritos = listaFavoritos;
    }

    @NonNull
    @Override
    public FavoritosAdapter.FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item,null,false);
        view.setOnClickListener(this);
        return new FavoritosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosAdapter.FavoritosViewHolder holder, int position) {
        holder.titulo.setText(listaFavoritos.get(position).getTitulo());
        holder.codigo.setText(listaFavoritos.get(position).getCodigo());
        holder.contenido.setText(listaFavoritos.get(position).getContent());
        holder.url.setText(listaFavoritos.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public void setOnClicListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class FavoritosViewHolder extends RecyclerView.ViewHolder{

        TextView titulo, codigo, url, contenido;

        public FavoritosViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.favoriteTitle);
            codigo = (TextView) itemView.findViewById(R.id.favoriteId);
            url = (TextView) itemView.findViewById(R.id.favoriteUrl);
            contenido = (TextView) itemView.findViewById(R.id.favoriteContent);
        }
    }
}
