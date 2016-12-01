package mbd.org.roadtrip.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

import mbd.org.roadtrip.Models.Message;

/**
 * Created by derekyu on 11/23/16.
 */

public class FirebaseUtil {
    private static FirebaseAuth mAuth;
    private static FirebaseDatabase mDatabase;
    private static String MESSAGE_CHILD = "messages";
    private static String MEMBERS_CHILD = "members";
    private static String CHATS_CHILD = "chats";
    private static String USERS_CHILD = "users";
    private static String ORDER_TIME_STAMP = "timestamp";


    public static void addNewGroup(){

    }

    public static void sendNewMessage(String message, String group){
        getInstanceDatabase();
        getInstanceAuth();
        Message mMessage = new Message(mAuth.getCurrentUser().getDisplayName(),message, mAuth.getCurrentUser().getEmail());
        mDatabase.getReference().child(MESSAGE_CHILD).child(group).push().setValue(mMessage);

    }


    private static void getInstanceAuth(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }
    }

    private static void getInstanceDatabase(){
        if(mDatabase == null){
            mDatabase = FirebaseDatabase.getInstance();
        }
    }

    public static Query getMessagesForGroup(String groupID){
        getInstanceDatabase();
        return mDatabase.getReference().child(MESSAGE_CHILD).child(groupID).orderByChild(ORDER_TIME_STAMP);

    }

    public static Query getGroups(){
        getInstanceDatabase();
        getInstanceAuth();
        return mDatabase.getReference().child(USERS_CHILD).child(getUser().getUid());
    }

    public static FirebaseUser getUser(){
        getInstanceAuth();
        return mAuth.getCurrentUser();
    }


}
