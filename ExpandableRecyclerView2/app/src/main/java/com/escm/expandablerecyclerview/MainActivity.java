package com.escm.expandablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        expandableListView =  findViewById(R.id.elvMobiles);
        expandableListAdapter =  new MyExpandibleListAdapter(this, groupList, mobileCollection);
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
                Toast.makeText(MainActivity.this, "Selected: " + selected , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createGroupList() {
        groupList =  new ArrayList<>();
        groupList.add("Samsung");
        groupList.add("Google");
        groupList.add("Redmi");
        groupList.add("Vivo");
        groupList.add("Nokia");
        groupList.add("Motorola");
    }

    private void createCollection() {
        String[] samsungModels = {"01","02","03"};
        String[] googleModels = {"04","05","06","07","08"};
        String[] redmiModels = {"09","10"};
        String[] vivoModels = {"11","12","13"};
        String[] nokiaModels = {"14","15"};
        String[] motorolaModels = {"16","17","18","19","20"};

        mobileCollection = new HashMap<String, List<String>>();
        for(String group: groupList){
            if(group.equals("Samsung")){
                loadChild(samsungModels);
            }
            if(group.equals("Google")){
                loadChild(googleModels);
            }
            if(group.equals("Redmi")){
                loadChild(redmiModels);
            }
            if(group.equals("Vivo")){
                loadChild(vivoModels);
            }
            if(group.equals("Nokia")){
                loadChild(nokiaModels);
            }
            if(group.equals("Motorola")){
                loadChild(motorolaModels);
            }

            mobileCollection.put(group, childList);
        }

    }

    private void loadChild(String[] mobileModels) {
        childList =  new ArrayList<>();
        for(String model: mobileModels){
            childList.add(model);
        }
    }

}