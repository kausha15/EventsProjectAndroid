package com.project.event.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.project.event.R;
import com.project.event.pojo.EventDetail;

import java.util.ArrayList;

public class EventsListAdapter extends BaseAdapter {

    private ArrayList<EventDetail> eventsList;
    private LayoutInflater inflater;
    private Context context;

    public EventsListAdapter(Context context, ArrayList<EventDetail> eventsList){
        this.eventsList = eventsList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventsList.size();
    }

    @Override
    public EventDetail getItem(int position) {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View crow = convertView;
        ViewHolder holder;

        EventDetail eventDetail = getItem(position);
        if(crow != null){
            crow = inflater.inflate(R.layout.crow_event_items,null,false);
            holder = new ViewHolder();




        }else{
            holder = (ViewHolder) crow.getTag();
        }
        return null;
    }

    private class ViewHolder{

    }
}
