package com.escm.lbn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.escm.lbn.R;
import com.escm.lbn.adapter.MyExpandibleListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctrinaActivity extends AppCompatActivity {

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private AdView mAdView;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> doctrinaCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctrina);

        //Poblar lista desplegable
        createGroupList();
        createCollection();

        expandableListView =  findViewById(R.id.evDoctrina);
        expandableListAdapter =  new MyExpandibleListAdapter(this, groupList, doctrinaCollection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = -1;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String selected = expandableListAdapter.getChild(i,i1).toString();
                //Toast.makeText(DoctrinaActivity.this, "Selected: " + selected , Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //mostrar anuncios
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adViewBannerDoctrina);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Barra de herramientas
        toolbar = (Toolbar) findViewById(R.id.toolbarDoctrina);
        setUpToolbar();

        //Panel de navegacion lateral
        navigationView = findViewById(R.id.idNavigationViewDoctrina); //menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        Intent intent = new Intent(DoctrinaActivity.this,PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_doctrina:
                        Intent intent5 = new Intent(DoctrinaActivity.this,DoctrinaActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case R.id.nav_convicciones:
                        Intent intent6 = new Intent(DoctrinaActivity.this,ConviccionesActivity.class);
                        startActivity(intent6);
                        finish();
                        break;
                    case R.id.nav_favoritos:
                        Intent intent4 = new Intent(DoctrinaActivity.this,FavoritosActivity.class);
                        startActivity(intent4);
                        finish();
                        break;
                    case R.id.nav_contacto:
                        Intent intent7 = new Intent(DoctrinaActivity.this,SendMailActivity.class);
                        startActivity(intent7);
                        finish();
                        break;
                    case R.id.nav_about:
                        Intent intent2 = new Intent(DoctrinaActivity.this,AboutActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case R.id.nav_youtube:
                        Intent intent8 = new Intent(DoctrinaActivity.this,YoutubeActivity.class);
                        startActivity(intent8);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    private void setUpToolbar() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutDoctrina);
        toolbar = findViewById(R.id.toolbarDoctrina);
        toolbar.setTitle(R.string.doctrina);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void createGroupList() {
        groupList =  new ArrayList<>();
        groupList.add(getResources().getString(R.string.textoDoctrina1));
        groupList.add(getResources().getString(R.string.textoDoctrina2));
        groupList.add(getResources().getString(R.string.textoDoctrina3));
        groupList.add(getResources().getString(R.string.textoDoctrina4));
        groupList.add(getResources().getString(R.string.textoDoctrina5));
        groupList.add(getResources().getString(R.string.textoDoctrina6));
        groupList.add(getResources().getString(R.string.textoDoctrina7));
        groupList.add(getResources().getString(R.string.textoDoctrina8));
        groupList.add(getResources().getString(R.string.textoDoctrina9));
        groupList.add(getResources().getString(R.string.textoDoctrina10));
        groupList.add(getResources().getString(R.string.textoDoctrina11));
        groupList.add(getResources().getString(R.string.textoDoctrina12));
        groupList.add(getResources().getString(R.string.textoDoctrina13));
        groupList.add(getResources().getString(R.string.textoDoctrina14));
        groupList.add(getResources().getString(R.string.textoDoctrina15));
        groupList.add(getResources().getString(R.string.textoDoctrina16));
        groupList.add(getResources().getString(R.string.textoDoctrina17));
    }

    private void createCollection() {
        String[] versos01 = {getResources().getString(R.string.versosDoctrina1a),getResources().getString(R.string.versosDoctrina1b)};
        String[] versos02 = {getResources().getString(R.string.versosDoctrina2a),getResources().getString(R.string.versosDoctrina2b),getResources().getString(R.string.versosDoctrina2c),getResources().getString(R.string.versosDoctrina2d),getResources().getString(R.string.versosDoctrina2e),getResources().getString(R.string.versosDoctrina2f),getResources().getString(R.string.versosDoctrina2g)};
        String[] versos03 = {getResources().getString(R.string.versosDoctrina3a),getResources().getString(R.string.versosDoctrina3b),getResources().getString(R.string.versosDoctrina3c),getResources().getString(R.string.versosDoctrina3d),getResources().getString(R.string.versosDoctrina3e)};
        String[] versos04 = {getResources().getString(R.string.versosDoctrina4a),getResources().getString(R.string.versosDoctrina4b),getResources().getString(R.string.versosDoctrina4c)};
        String[] versos05 = {getResources().getString(R.string.versosDoctrina5a),getResources().getString(R.string.versosDoctrina5b)};
        String[] versos06 = {getResources().getString(R.string.versosDoctrina6a),getResources().getString(R.string.versosDoctrina6b)};
        String[] versos07 = {getResources().getString(R.string.versosDoctrina7a),getResources().getString(R.string.versosDoctrina7b)};
        String[] versos08 = {getResources().getString(R.string.versosDoctrina8a),getResources().getString(R.string.versosDoctrina8b),getResources().getString(R.string.versosDoctrina8c),getResources().getString(R.string.versosDoctrina8d)};
        String[] versos09 = {getResources().getString(R.string.versosDoctrina9a)};
        String[] versos10 = {getResources().getString(R.string.versosDoctrina10a)};
        String[] versos11 = {getResources().getString(R.string.versosDoctrina11a),getResources().getString(R.string.versosDoctrina11b),getResources().getString(R.string.versosDoctrina11c),getResources().getString(R.string.versosDoctrina11d)};
        String[] versos12 = {getResources().getString(R.string.versosDoctrina12a),getResources().getString(R.string.versosDoctrina12b)};
        String[] versos13 = {getResources().getString(R.string.versosDoctrina13a),getResources().getString(R.string.versosDoctrina13b)};
        String[] versos14 = {getResources().getString(R.string.versosDoctrina14a),getResources().getString(R.string.versosDoctrina14b),getResources().getString(R.string.versosDoctrina14c)};
        String[] versos15 = {getResources().getString(R.string.versosDoctrina15a),getResources().getString(R.string.versosDoctrina15b),getResources().getString(R.string.versosDoctrina15c),getResources().getString(R.string.versosDoctrina15d),getResources().getString(R.string.versosDoctrina15e)};
        String[] versos16 = {getResources().getString(R.string.versosDoctrina16a),getResources().getString(R.string.versosDoctrina16b),getResources().getString(R.string.versosDoctrina16c)};
        String[] versos17 = {getResources().getString(R.string.versosDoctrina17a),getResources().getString(R.string.versosDoctrina17b),getResources().getString(R.string.versosDoctrina17c)};

        doctrinaCollection = new HashMap<String, List<String>>();
        for(String group: groupList){
            if(group.equals(getResources().getString(R.string.textoDoctrina1))){
                loadChild(versos01);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina2))){
                loadChild(versos02);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina3))){
                loadChild(versos03);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina4))){
                loadChild(versos04);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina5))){
                loadChild(versos05);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina6))){
                loadChild(versos06);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina7))){
                loadChild(versos07);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina8))){
                loadChild(versos08);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina9))){
                loadChild(versos09);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina10))){
                loadChild(versos10);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina11))){
                loadChild(versos11);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina12))){
                loadChild(versos12);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina13))){
                loadChild(versos13);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina14))){
                loadChild(versos14);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina15))){
                loadChild(versos15);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina16))){
                loadChild(versos16);
            }
            if(group.equals(getResources().getString(R.string.textoDoctrina17))){
                loadChild(versos17);
            }

            doctrinaCollection.put(group, childList);
        }

    }

    private void loadChild(String[] doctrinaModels) {
        childList =  new ArrayList<>();
        for(String model: doctrinaModels){
            childList.add(model);
        }
    }

}