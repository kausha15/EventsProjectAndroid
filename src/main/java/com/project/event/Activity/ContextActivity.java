package com.project.event.Activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.project.event.R;

import java.io.File;

public class ContextActivity extends ActionBarActivity {

    public View setCustomView(String title, int backgroundColor, int statusbarcolor)
    {
        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_action_bar,null);

        ActionBar.LayoutParams lp1 = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        RelativeLayout rlCustomActionBar = (RelativeLayout) view.findViewById(R.id.rlCustomActionBar);
        ImageButton ivLeftImage = (ImageButton) view.findViewById(R.id.ivLeftImage);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView btnPost = (TextView) view.findViewById(R.id.tvPost);

//        SelectorClass selectorClass=new SelectorClass();
        //btnPost.setOnTouchListener(selectorClass);
//        ivLeftImage.setOnTouchListener(selectorClass);
        btnPost.setVisibility(View.GONE);

        rlCustomActionBar.setBackgroundColor(getResources().getColor(backgroundColor));
        tvTitle.setText(Html.fromHtml(title));

//        ivLeftImage.setBackgroundResource(R.drawable.ic_action_back);

        ivLeftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        actionBar.setCustomView(view,lp1);

        setStatusBarColor(statusbarcolor);
        return view;
    }

    public void setActionBarTitle(String title){
        View actionbar = getSupportActionBar().getCustomView();
        ((TextView)actionbar.findViewById(R.id.tvTitle)).setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        File cacheDir = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).
                memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024).
                diskCache(new UnlimitedDiscCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100).build();



        ImageLoader.getInstance().init(config);

        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setStatusBarColor(R.color.black);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    protected void setStatusBarColor(int color)
    {
        try {
            if (Build.VERSION.SDK_INT >= 21) {

                getWindow().setStatusBarColor(getResources().getColor(color));
            }

        }
        catch (Exception e)
        {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
