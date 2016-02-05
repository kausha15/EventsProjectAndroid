package com.project.event.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;

import com.project.event.Activity.MainActivity;
import com.project.event.UtilityFunctions.Logger;
import com.project.event.UtilityFunctions.Utilities;
import com.project.event.helpers.UIHandler;

import org.apache.http.NameValuePair;

import java.util.List;

public class GetDataFromURL extends AsyncTask<Void,Void,Void> {

    private List<NameValuePair> params;
    private UIHandler handler;
    private String response;
    private String url;
    private int method;
    private boolean isMessageSent;
    private boolean isAuthKeyToBeAdded;
    private Context context;
    public GetDataFromURL(List<NameValuePair> params, UIHandler uihandler, String url, int method, Context context)
    {
        initializeVariables(params,uihandler,url,method,context,false);
    }

    private void initializeVariables(List<NameValuePair> params, UIHandler uihandler, String url, int method, Context context,boolean isAuthKeyToBeAdded)
    {
        this.params=params;
        handler=uihandler;
        this.url=url;
        this.method=method;
        this.context=context;
        isMessageSent=false;
        this.isAuthKeyToBeAdded=isAuthKeyToBeAdded;

        if(!url.contains(MainActivity.HOST_URL))
        {
            this.url=MainActivity.HOST_URL+"/"+url;
        }


    }
    @Override
    protected Void doInBackground(Void... voids) {

        Logger.log("data"," --- IN BKG -----");

        if(Utilities.isNetworkAvailable(context)) {
            Logger.log("data"," --- IN IF -----");
            if(isCancelled())
            {
                return null;
            }
            Logger.log("data"," --- IN IF 1 -----");
            response = Utilities.sendDataToServer(url, method, params);
        }
        else
        {
            handler.sendEmptyMessage(UIHandler.CALL_UNSUCCESSFUL);
            isMessageSent=true;
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(response==null)
        {
            if(!isMessageSent)
                handler.sendEmptyMessage(UIHandler.CALL_UNSUCCESSFUL);
        }
        else
        {

            Message msg=handler.obtainMessage();
            msg.what=UIHandler.CALL_SUCCESSFULL;
            msg.obj=response;
            handler.sendMessage(msg);
        }
    }
}
