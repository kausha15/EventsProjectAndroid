package com.project.event.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.event.Activity.MainActivity;
import com.project.event.Adapters.EventsListAdapter;
import com.project.event.R;
import com.project.event.pojo.EventDetail;

import java.util.ArrayList;

public class EventListFragment extends Fragment {

    private EventsListAdapter eventsListAdapter;
    private ArrayList<EventDetail> eventDetailArrayList;

    private ListView lsEvents;
    private TextView tvEmptyListViewText;
    private ProgressBar pbLoadingProgress;

    public EventListFragment() {}

    public static EventListFragment newInstance(String param1, String param2) {
        EventListFragment fragment = new EventListFragment();
        return fragment;
    }

    public void updateList(ArrayList<EventDetail> eventDetailArrayList){
        this.eventDetailArrayList = eventDetailArrayList;
        eventsListAdapter.notifyDataSetChanged();
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

        ((MainActivity)getActivity()).getAllEvents(getActivity());

        eventsListAdapter = new EventsListAdapter(getActivity(), eventDetailArrayList);
        lsEvents.setAdapter(eventsListAdapter);
        lsEvents.setDividerHeight(15);
        lsEvents.setDivider(null);

        tvEmptyListViewText.setText("No Events To Display ");
        pbLoadingProgress.setVisibility(View.GONE);

        return view;
    }

    public void onButtonPressed(Uri uri) {}



    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
