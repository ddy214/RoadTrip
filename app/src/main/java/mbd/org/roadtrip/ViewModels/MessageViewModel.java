package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Message;
import mbd.org.roadtrip.Utils.DialogGenerator;

/**
 * Created by derekyu on 11/20/16.
 */

public class MessageViewModel {
    private Context mContext;
    private Message mMessage;
    private Calendar mCalendar;
    private Chats mChats;

    public MessageViewModel(Context context, Message message, Chats chats){
        mContext = context;
        mMessage = message;
        mChats = chats;
    }

    public String getName(){
        return mMessage.name;
    }

    public String getMessage(){
        return mMessage.message;
    }

    public View.OnLongClickListener setupScheduleEvent(){
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d(mChats.destination,"Destination");
                DialogGenerator.createEventDialog(mContext, mMessage.message, mChats).show();
                return true;
            }
        };
    }
}
