package com.escm.lbn.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.escm.lbn.R;

import java.util.List;
import java.util.Map;

public class MyExpandibleListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<String, List<String>> doctrinaCollection;
    private List<String> groupList;

    public MyExpandibleListAdapter(Context context, List<String> groupList, Map<String, List<String>> doctrinaCollection) {
        this.context = context;
        this.doctrinaCollection = doctrinaCollection;
        this.groupList = groupList;
    }

    @Override
    public int getGroupCount() {
        return doctrinaCollection.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return doctrinaCollection.get(groupList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return doctrinaCollection.get(groupList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String doctrinaPostulado = getGroup(i).toString();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doctrina_grupo, null);
        }
        TextView item = (TextView) view.findViewById(R.id.textoGrupoDoctrina);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(doctrinaPostulado);
        ImageView imagen = (ImageView) view.findViewById(R.id.bibliaIcon);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String versiculos = doctrinaCollection.get(groupList.get(i)).get(i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.doctrina_items, null);
        }
        TextView item = (TextView) view.findViewById(R.id.itemTextoDoctrina);
        item.setText(versiculos);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
