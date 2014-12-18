package com.company.pellet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Products> objects;

    ListAdapter(Context context, ArrayList<Products> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        Products p = getProduct(position);

        ((TextView) view.findViewById(R.id.tvData)).setText(p.date);
        ((TextView) view.findViewById(R.id.tvProduct)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvDestination)).setText(p.path);
        ((TextView) view.findViewById(R.id.tvId)).setText(p.id);

        return view;
    }

    // товар по позиции
    Products getProduct(int position) {
        return ((Products) getItem(position));
    }

}