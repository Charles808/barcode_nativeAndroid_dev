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

    // These three function is not used yet, but must be defined
    // since this is not Abstract class
    public int getCount() { return searchArrayList.size(); }
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    // Get a View that displays the data at the specified position in the data set, inflated it from an XML layout file.
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Inflate view with our custom xml file
            convertView = mInflater.inflate(R.layout.activity_historylist, null);
            // Definition of our holder
            holder = new ViewHolder();
            // Chaining holder member with related id in their respective xml
            holder.txtScan = (TextView) convertView.findViewById(R.id.scanListTxt);
            holder.txtFormat = (TextView) convertView.findViewById(R.id.formatListTxt);
            holder.txtTime = (TextView) convertView.findViewById(R.id.timeListTxt);

            // Save holder to memory
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Set member value to list view
        holder.txtScan.setText(searchArrayList.get(position).getScanResult());
        holder.txtFormat.setText(searchArrayList.get(position).getFormatResult());
        holder.txtTime.setText(searchArrayList.get(position).getTimeResult());

        return convertView;
    }

    // Holder for items which will be displayed on each row
    static class ViewHolder {
        TextView txtScan;
        TextView txtFormat;
        TextView txtTime;
    }
}
