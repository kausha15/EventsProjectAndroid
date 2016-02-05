package com.project.event.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.event.Activity.MainActivity;
import com.project.event.Adapters.EventsListAdapter;
import com.project.event.CustomWidgets.ConfirmationWindow;
import com.project.event.R;
import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;
import com.project.event.helpers.UIHandler;
import com.project.event.pojo.EventDetail;
import com.project.event.task.GetDataFromURL;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment {

    private EventsListAdapter eventsListAdapter;
    private ArrayList<EventDetail> eventDetailArrayList;

    private ListView lsEvents;
    private TextView tvEmptyListViewText;
    private ProgressBar pbLoadingProgress;

    private ProgressDialog progressDialog;

    public EventListFragment() {}

    public  EventListFragment newInstance(ArrayList<EventDetail> eventDetailArrayList) {
        EventListFragment fragment = new EventListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        eventDetailArrayList = new ArrayList<EventDetail>();
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        lsEvents = (ListView) view.findViewById(R.id.lsGeneralListView);
        tvEmptyListViewText = (TextView) view.findViewById(R.id.tvEmptyList);
        pbLoadingProgress = (ProgressBar) view.findViewById(R.id.pbEmptyList);

        lsEvents.setEmptyView(view.findViewById(R.id.llEmptyList));
        tvEmptyListViewText.setText(" Loading Events ");

        eventsListAdapter = new EventsListAdapter(getActivity(), eventDetailArrayList);
        lsEvents.setAdapter(eventsListAdapter);
        lsEvents.setDividerHeight(15);
        lsEvents.setDivider(null);

        getAllEvents(getActivity());

        tvEmptyListViewText.setText("No Events To Display ");
        pbLoadingProgress.setVisibility(View.GONE);

        return view;
    }

    public void getAllEvents(Context context){
        if(Utilities.isNetworkAvailable(context)){
            Logger.log("fetch", " fetch all events ");
            List<NameValuePair> params = new ArrayList<>();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading Events ...");
            progressDialog.show();
            new GetDataFromURL(params,handleResponse,MainActivity.HOST_URL + "/fetchAllEvents",MainActivity.POST,context).execute();
        }else{
            Utilities.showNetworkNotAvailableDialog(context);
        }

    }

    UIHandler handleResponse = new UIHandler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.dismiss();
            switch (msg.what){
                case CALL_SUCCESSFULL:
                    try{
                        JSONObject jsonObject = new JSONObject(msg.obj.toString());
                        JSONArray eventList = jsonObject.getJSONObject("data").getJSONArray("event_list");
                        int eventListSize = eventList.length();
                        Logger.log("fetch"," event list size ------ "+eventListSize);
                        Gson gson = new Gson();
                        eventDetailArrayList.clear();

                        for(int i=0; i<eventListSize;i++){
                            EventDetail eventDetail = gson.fromJson(eventList.getJSONObject(i).toString(), EventDetail.class);
                            eventDetailArrayList.add(eventDetail);
                        }
                        eventsListAdapter.notifyDataSetChanged();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case CALL_UNSUCCESSFUL:
                    new ConfirmationWindow(getActivity(),"Failed","We could not fetch Events, Please Try Again !","Try Again","Cancel"){
                        @Override
                        protected void setPositiveResponse() {
                            super.setPositiveResponse();
                            getAllEvents(getActivity());
                        }

                    };
                    break;
            }

        }
    };

    public void onButtonPressed(Uri uri) {}



    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
