package com.womenproiot.www.link;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MySpinner extends ArrayAdapter<MeetUpDto> {

    LayoutInflater inflater;
    ArrayList<MeetUpDto> objects;
    ViewHolder holder = null;

    public MySpinner(Context context, int resource, List<MeetUpDto> objects) {
        super(context, resource, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.objects = (ArrayList<MeetUpDto>) objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        MeetUpDto listItem = objects.get(position);
        View row = convertView;

        if (null == row) {
            holder = new ViewHolder();
            row = inflater.inflate(R.layout.spinner_item, parent, false);
            holder.seq = (TextView) row.findViewById(R.id.textViewSeq);
            holder.title = (TextView) row.findViewById(R.id.textViewTitle);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        holder.seq.setText(listItem.seq);
        holder.title.setText(listItem.title);

        return row;
    }

    public class ViewHolder {
        TextView seq, title;
    }
}
