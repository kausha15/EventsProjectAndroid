package com.project.event.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.project.event.R;
import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;
import com.project.event.fragments.WelcomeFragment;
import com.project.event.pojo.EventDetail;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private FrameLayout flFragment;

    public static final int POST = 1;
    public static final int GET = 2;
    public static String HOST_URL = "https://apartmentadda.com/api";

    public ArrayList<EventDetail> eventDetailArrayList = new ArrayList<EventDetail>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flFragment = (FrameLayout) findViewById(R.id.flFragment);

        getSupportActionBar().setTitle("Connect");
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.action_bar_color));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        fragmentTransaction.add(R.id.flFragment, welcomeFragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAllEvents(Context context){
        if(Utilities.isNetworkAvailable(context)){
            Logger.log("fetch", " fetch all events ");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String response= Utilities.sendDataToServer(HOST_URL + "/fetchAllEvents", POST, params);
            handleResponse(response);
        }else{
            Utilities.showNetworkNotAvailableDialog(context);
        }

    }

    public void handleResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray eventList = jsonObject.getJSONObject("data").getJSONArray("event_list");
            int eventListSize = eventList.length();
            Logger.log("fetch"," event list size ------ "+eventListSize);
            Gson gson = new Gson();
            eventDetailArrayList.clear();

            for(int i=0; i<eventListSize;i++){
                EventDetail eventDetail = gson.fromJson(eventList.getJSONObject(i).toString(), EventDetail.class);
                eventDetailArrayList.add(eventDetail);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
