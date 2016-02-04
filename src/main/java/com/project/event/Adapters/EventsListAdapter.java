package com.project.event.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.event.Activity.MainActivity;
import com.project.event.R;
import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;
import com.project.event.fragments.SingleEventFragment;
import com.project.event.pojo.EventDetail;

import java.io.Serializable;
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

        final EventDetail eventDetail = getItem(position);
        if(crow != null){
            crow = inflater.inflate(R.layout.crow_event_items,null,false);
            holder = new ViewHolder();

            holder.cvEvent = (CardView) crow.findViewById(R.id.cvEvent);
            holder.ivEventImage = (ImageView) crow.findViewById(R.id.ivEventImage);
            holder.tvEventName = (TextView) crow.findViewById(R.id.tvEventName);

        }else{
            holder = (ViewHolder) crow.getTag();
        }
        if(!eventDetail.getImageUrl().equals("")){
            holder.ivEventImage.setImageBitmap(Utilities.downloadBitmap(eventDetail.getImageUrl()));
        }
        holder.tvEventName.setText(eventDetail.getName());

        holder.cvEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.log("display"," --- CARD VIEW ON CLICK --------- "+eventDetail.getId());
                SingleEventFragment singleEventFragment = new SingleEventFragment();
                Bundle b =new Bundle();
                b.putSerializable(MainActivity.EVENT_OBJECT, (Serializable) eventDetail);
                singleEventFragment.setArguments(b);
                Logger.log("discover", " click on button ");
                ((MainActivity) context).getFragmentManager().beginTransaction().replace(R.id.flFragment, singleEventFragment).addToBackStack(null).commit();
            }
        });
        return crow;
    }

    private class ViewHolder{

        CardView cvEvent;
        ImageView ivEventImage;
        TextView tvEventName;

    }
}
