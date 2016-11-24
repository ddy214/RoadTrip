package mbd.org.roadtrip.ViewModels;

import android.content.Context;

import com.google.firebase.database.FirebaseDatabase;

import mbd.org.roadtrip.Models.Message;

/**
 * Created by derekyu on 11/20/16.
 */

public class MessageViewModel {
    private Context mContext;
    private Message mMessage;


    public MessageViewModel(Context context, Message message){
        mContext = context;
        mMessage = message;
    }

    public String getName(){
        return mMessage.name;
    }

    public String getMessage(){
        return mMessage.message;
    }
}
