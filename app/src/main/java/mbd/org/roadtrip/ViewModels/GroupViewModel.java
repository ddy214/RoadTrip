package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import java.util.Calendar;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Views.Activities.MenuActivity;

/**
 * Created by derekyu on 11/23/16.
 */

public class GroupViewModel extends BaseObservable {
    private Context mContext;
    private Chats mChats;


    public GroupViewModel(Context context, Chats chats){
        mContext = context;
        mChats = chats;
    }

    public String getLastMessage(){
        return mChats.lastMessage;
    }

    public String getTripEnd(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mChats.end);
        return (cal.get(Calendar.MONTH)+1)+"/" +cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    public String getDestination(){
        return mChats.destination;
    }

    public String getNumMembers(){
        return mChats.members+"";
    }

    public String getName(){
        return mChats.title;
    }
    public String getTripStart(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mChats.start);
        return (cal.get(Calendar.MONTH)+1)+"/" +cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    public View.OnClickListener handleClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(MenuActivity.getMenuActivity(mContext, mChats));
            }
        };
    }
}
