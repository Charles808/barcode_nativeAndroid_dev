package com.example.charlessuresoft.barcodescanner_dev1;

/**
* Created by Charles Suresoft on 30/11/2016.
*/

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {
    private static ArrayList<SearchResult> searchArrayList;

    private LayoutInflater mInflater;

    public CustomBaseAdapter(Context context, ArrayList<SearchResult> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList.size();
    }

    public Object getItem(int position) {
        return searchArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_historylist, null);
            holder = new ViewHolder();
            holder.txtScan = (TextView) convertView.findViewById(R.id.name);
            holder.txtFormat = (TextView) convertView.findViewById(R.id.cityState);
            holder.txtTime = (TextView) convertView.findViewById(R.id.phone);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtScan.setText(searchArrayList.get(position).getScanResult());
        holder.txtFormat.setText(searchArrayList.get(position).getFormatResult());
        holder.txtTime.setText(searchArrayList.get(position).getTimeResult());

        return convertView;
    }

    static class ViewHolder {
        TextView txtScan;
        TextView txtFormat;
        TextView txtTime;
    }
}
