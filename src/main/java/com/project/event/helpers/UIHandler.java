package com.project.event.helpers;


import android.os.Handler;

public class UIHandler extends Handler{

    public final static int CALL_SUCCESSFULL=-1;
    public final static int CALL_UNSUCCESSFUL=-2;

    private int currentAction=-1;

    public void setAction(int action)
    {
        currentAction=action;
    }

    public int getCurrentAction()
    {
        return  currentAction;
    }






}
