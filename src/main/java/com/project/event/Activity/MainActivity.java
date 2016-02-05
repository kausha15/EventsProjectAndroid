package com.project.event.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.project.event.R;
import com.project.event.fragments.WelcomeFragment;
import com.project.event.pojo.EventDetail;

import java.util.ArrayList;


public class MainActivity extends ContextActivity {

    private FrameLayout flFragment;

    public static final int POST = 1;
    public static final int GET = 2;
    public static String HOST_URL = "http://192.168.1.48/api";
    public static final String EVENT_OBJECT = "event_object";

    public ArrayList<EventDetail> eventDetailArrayList = new ArrayList<EventDetail>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flFragment = (FrameLayout) findViewById(R.id.flFragment);


        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.action_bar_color));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Connect");
//        setCustomView("Custom",R.color.action_bar_color,R.color.black);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        fragmentTransaction.add(R.id.flFragment, welcomeFragment);
        fragmentTransaction.addToBackStack("1");
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



}
