package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.view.View;

import java.util.Calendar;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Views.Activities.ScheduleActivity;

/**
 * Created by derekyu on 12/14/16.
 */

public class MenuViewModel {
    private Context mContext;
    private Chats mChat;

    public MenuViewModel(Context context, Chats chats){
        mContext = context;
        mChat = chats;
    }

    public View.OnClickListener getConversation(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ButtonPress) mContext).handleConversation();
            }
        };
    }

    public View.OnClickListener getSchedule(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(ScheduleActivity.getScheduleActivity(mContext, mChat));
            }
        };
    }

    public View.OnClickListener getSettings(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ButtonPress) mContext).handleSettings();
            }
        };
    }

    public interface ButtonPress{

        public void handleConversation();

        public void handleSettings();
    }

    public String getNumMembers(){
        return mChat.members+"";
    }

    public String getMembers(){
        String members = "";
        return members;
    }

    public String getTitle(){
        return mChat.title;
    }

    public String getStart(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mChat.start);
        return (cal.get(Calendar.MONTH)+1)+"/" +cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    public String getEnd(){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mChat.end);
        return (cal.get(Calendar.MONTH)+1)+"/" +cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
    }

    public String getDestination(){
        return mChat.destination;
    }

}
