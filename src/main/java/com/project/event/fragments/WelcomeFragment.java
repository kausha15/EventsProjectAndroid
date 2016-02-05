package com.project.event.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.event.R;
import com.project.event.UtilityFunctions.Logger;

public class WelcomeFragment extends Fragment {

    private Button btnDiscover;

    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        return fragment;
    }

    public WelcomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        btnDiscover = (Button) view.findViewById(R.id.btnDiscover);

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.log("discover", " click on button ");
//                ((MainActivity)getActivity()).getAllEvents(getActivity());
                EventListFragment eventListFragment = new EventListFragment();
                Logger.log("discover", " click on button ");
                getActivity().getFragmentManager().beginTransaction().replace(R.id.flFragment, eventListFragment).addToBackStack("2").commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
//android:background="#fdedc7"