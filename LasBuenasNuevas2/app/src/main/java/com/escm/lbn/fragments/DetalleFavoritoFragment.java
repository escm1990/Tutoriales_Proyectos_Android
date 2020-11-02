package com.escm.lbn.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.escm.lbn.R;
import com.escm.lbn.entities.Favorito;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class DetalleFavoritoFragment extends Fragment {

    String urlPost;
    String titlePost;
    String contentPost;
    String idPost;

    TextView txtTitle;
    HtmlTextView visorHtml;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_favorito,container,false);

        txtTitle = view.findViewById(R.id.txtTitlePostDFX);
        visorHtml = view.findViewById(R.id.html_textDFX);

    //Crear bundle para recibir el objeto enviado por parametro.
    Bundle objetoFavorito = getArguments();
    Favorito favorito = null;;
    //validacion para verificar si existen argumentos para mostrar
        if(objetoFavorito !=null){
        favorito = (Favorito) objetoFavorito.getSerializable("objeto");

        //CARGAR CONTENIDO DEL Favorito
        urlPost = favorito.getUrl();
        titlePost =  favorito.getTitulo();
        contentPost = favorito.getContent();
        idPost = favorito.getCodigo();

        txtTitle.setText(titlePost);
        visorHtml.setHtml(contentPost,new HtmlHttpImageGetter(visorHtml));
    }
        return view;
    }
}