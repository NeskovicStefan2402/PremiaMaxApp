package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;


public class Adapter extends BaseAdapter {

    Context context;
    private final LinkedList<String> values;
    private final LinkedList<String> numbers;

    public Adapter(Context context, LinkedList<String> values, LinkedList<String> numbers) {
        this.context = context;
        this.values = values;
        this.numbers = numbers;

    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.poruka_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtMail);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.txtPoruka);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.txtName.setText(values.get(position));
        viewHolder.txtVersion.setText(numbers.get(position)+"");

        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion;

    }

}

