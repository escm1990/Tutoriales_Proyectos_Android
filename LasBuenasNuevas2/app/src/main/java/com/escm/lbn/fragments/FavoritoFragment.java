package com.escm.lbn.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.escm.lbn.helpers.AdminSQLiteOpenHelper;
import com.escm.lbn.helpers.Constants;
import com.escm.lbn.entities.Favorito;
import com.escm.lbn.interfaces.IComunicaFragmentsFavoritos;
import com.escm.lbn.adapter.ListaFavoritosAdapter;
import com.escm.lbn.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritoFragment extends Fragment {

    String urlPost;
    String titlePost;
    String contentPost;
    String idPost;

    ArrayList<Favorito> listaFavoritos;
    RecyclerView recyclerFavoritos;
    ListaFavoritosAdapter adapterFavoritos;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    IComunicaFragmentsFavoritos interfaceComunicaFragments;

    public FavoritoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lista_favorito,container,false);
        listaFavoritos = new ArrayList<>();
        recyclerFavoritos = vista.findViewById(R.id.recyclerFavoritosId);
        recyclerFavoritos.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaFavoritos();
        adapterFavoritos = new ListaFavoritosAdapter(getContext(),listaFavoritos);
        recyclerFavoritos.setAdapter(adapterFavoritos);
        adapterFavoritos.setOnClicListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarFavorito(listaFavoritos.get(recyclerFavoritos.getChildAdapterPosition(view)));
                //luego en la activity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
        return vista;
    }

    private void llenarListaFavoritos() {
            //conectando con la BD
            AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(getContext(), Constants.bd_favoritos, null, 1);
            SQLiteDatabase BaseDeDatos = adminBD.getWritableDatabase(); //abrir base de datos en modo lectura/escritura

            Favorito favorito = null;

            Cursor cursor = BaseDeDatos.rawQuery("select codigo, titulo, url, contenido from favoritos order by titulo",null);
            while (cursor.moveToNext()){
                favorito = new Favorito();
                favorito.setCodigo(cursor.getString(0));
                favorito.setTitulo(cursor.getString(1));
                favorito.setUrl(cursor.getString(2));
                favorito.setContent(cursor.getString(3));

                listaFavoritos.add(favorito);
            }
            BaseDeDatos.close();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (IComunicaFragmentsFavoritos) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}