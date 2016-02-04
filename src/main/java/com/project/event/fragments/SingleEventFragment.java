package com.project.event.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.event.Activity.MainActivity;
import com.project.event.R;
import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;
import com.project.event.pojo.EventDetail;

public class SingleEventFragment extends Fragment {

    public EventDetail eventDetail;

    private Button btnInterested;
    private TextView tvNameLabel;
    private TextView tvNameValue;
    private TextView tvVenueLabel;
    private TextView tvVenueValue;
    private TextView tvScheduleLabel;
    private TextView tvScheduleValue;
    private TextView tvCostValue;
    private TextView tvCostLabel;

    private ImageView ivEventImage;

    public SingleEventFragment() {
        // Required empty public constructor
    }
    public static SingleEventFragment newInstance(String param1, String param2) {
        SingleEventFragment fragment = new SingleEventFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventDetail = getArguments() != null ? (EventDetail) getArguments().getSerializable(MainActivity.EVENT_OBJECT) : new EventDetail();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_event, container, false);

        btnInterested = (Button) view.findViewById(R.id.btnInterested);
        tvCostLabel = (TextView) view.findViewById(R.id.tvCostLabel);
        tvCostValue = (TextView) view.findViewById(R.id.tvCostValue);
        tvScheduleLabel = (TextView) view.findViewById(R.id.tvScheduleLabel);
        tvScheduleValue = (TextView) view.findViewById(R.id.tvScheduleValue);
        tvVenueLabel = (TextView) view.findViewById(R.id.tvVenueLabel);
        tvVenueValue = (TextView) view.findViewById(R.id.tvVenueValue);
        tvNameLabel = (TextView) view.findViewById(R.id.tvNameLabel);
        tvNameValue = (TextView) view.findViewById(R.id.tvNameValue);
        ivEventImage = (ImageView) view.findViewById(R.id.ivEventImage);

        tvCostValue.setText(eventDetail.getCost());
        tvNameValue.setText(eventDetail.getName());
        tvScheduleValue.setText(eventDetail.getStart()+" - "+eventDetail.getEnd());
        tvVenueValue.setText(eventDetail.getVenue());
        ivEventImage.setImageBitmap(Utilities.downloadBitmap(eventDetail.getImageUrl()));

        btnInterested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.log("interest"," i am interested clicked ");
            }
        });

        return view;


    }

    public void onButtonPressed(Uri uri) {
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
