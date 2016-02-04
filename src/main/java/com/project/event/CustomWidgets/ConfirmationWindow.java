package com.project.event.CustomWidgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmationWindow
{

    private AlertDialog.Builder builder;
	public ConfirmationWindow(Context context, String title, String message, String positiveTitle, String negativeTitle)
	{
        try {
            builder = new AlertDialog.Builder(context);
            if (!title.equals(""))
                builder.setTitle(title);
            builder.setMessage(message);
            if (!positiveTitle.equals("")) {
                builder.setPositiveButton(positiveTitle, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPositiveResponse();

                    }
                });
            }
            if (!negativeTitle.equals("")) {
                builder.setNegativeButton(negativeTitle, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setNegativeResponse();
                    }
                });

            }
            builder.setCancelable(false);
            builder.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

    public ConfirmationWindow(Context context, String title, String message, String positiveTitle, String negativeTitle, String neutralTitle)
    {
        try {
            builder = new AlertDialog.Builder(context);
            if (!title.equals(""))
                builder.setTitle(title);
            builder.setMessage(message);
            if (!positiveTitle.equals("")) {
                builder.setPositiveButton(positiveTitle, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setPositiveResponse();

                    }
                });
            }
            if (!negativeTitle.equals("")) {
                builder.setNegativeButton(negativeTitle, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setNegativeResponse();
                    }
                });

            }

            if(!neutralTitle.equals(""))
            {
                builder.setNeutralButton(neutralTitle,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setNeutralResponse();
                    }
                });
            }
            builder.setCancelable(false);
            builder.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    protected  void setNeutralResponse()
    {

    }
	protected void setPositiveResponse()
	{

	}

	protected void setNegativeResponse()
	{

	}

}
