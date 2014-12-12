package com.company.pellet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class ListAdapter extends ArrayAdapter {
    private Map<String, ArrayList<String >> data;
    private Context context;
    public String name;

    public ListAdapter(Context context, Map<String, ArrayList<String>> data) {
        super(context, R.layout.item);
        this.data = (Map<String, ArrayList<String>>) data;
        this.context = context;
    }

    @Override
    public int getCount() {
        // возвращаем количество элементов списка
        return data.size();
    }

    @Override
    public ArrayList<String> getItem(int position) {
        // получение одного элемента по индексу
        return data.get(String.valueOf(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getName() {
        return name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Объект для каждой из записей
//        String item = (String) data.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item, parent, false);

        // Находим наш TextView и пишем в него название из объекта
        ((TextView) view.findViewById(R.id.item1)).setText((CharSequence) data.get(String.valueOf(position)));

        return view;
    }
}
