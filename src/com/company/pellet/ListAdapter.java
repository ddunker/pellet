package com.company.pellet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private List data;
    private Context context;
    public String name;

    public ListAdapter(Context context, List data) {
        super(context, R.layout.item);
        this.data = data;
        this.context = context;
        this.name = name;
    }

    @Override
    public int getCount() {
        // возвращаем количество элементов списка
        return data.size();
    }

    @Override
    public String getItem(int position) {
        // получение одного элемента по индексу
        return data.get(position).getClass().getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public String getName() {
        return name;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Объект для каждой из записей
//        String item = (String) data.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // создаём View и указываем наполнить её из R.layout.item, т.е. из нашего item.xml
        View view = inflater.inflate(R.layout.item, parent, false);

        // Находим наш TextView и пишем в него название из объекта
        ((TextView) view.findViewById(R.id.list_name)).setText((CharSequence) data.get(position));

        return view;
    }
}
