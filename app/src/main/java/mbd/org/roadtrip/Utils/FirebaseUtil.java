package mbd.org.roadtrip.Utils;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.Models.Message;

/**
 * Created by derekyu on 11/23/16.
 */

public class FirebaseUtil {
    private static FirebaseUtil firebaseUtil;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private static String MESSAGE_CHILD = "messages";
    private static String MEMBERS_CHILD = "members";
    private static String CHATS_CHILD = "chats";
    private static String USERS_CHILD = "users";
    private static String ORDER_TIME_STAMP = "timestamp";
    private static String ORDER_BY_DATE = "date";
    private static String SCHEDULES_CHILD = "schedules";
    private static String GROUPS_CHILD = "groups";
    private static String PEOPLE_CHILD = "people";
    private static String USERNAME_CHILD = "usernames";


    public void sendNewMessage(String message, String group){
        getInstanceDatabase();
        getInstanceAuth();
        Message mMessage;
        if(mAuth.getCurrentUser().getDisplayName() == null){
            mMessage = new Message("Bing Xu",message, mAuth.getCurrentUser().getEmail());

        } else {
           mMessage = new Message(mAuth.getCurrentUser().getDisplayName(),message, mAuth.getCurrentUser().getEmail());

        }
        mDatabase.getReference().child(MESSAGE_CHILD).child(group).push().setValue(mMessage);

    }


    public void createNewEvent(Event event, String group){
        mDatabase.getReference().child(SCHEDULES_CHILD).child(group).push().setValue(event);
    }

    public Query getChats(String group){
        return mDatabase.getReference().child(CHATS_CHILD).child(group);
    }

    private void getInstanceAuth(){
        if(mAuth == null){
            mAuth = FirebaseAuth.getInstance();
        }
    }

    private void getInstanceDatabase(){
        if(mDatabase == null){
            mDatabase = FirebaseDatabase.getInstance();
        }
    }

    public Query getMessagesForGroup(String groupID){
        getInstanceDatabase();
        return mDatabase.getReference().child(MESSAGE_CHILD).child(groupID).orderByChild(ORDER_TIME_STAMP);

    }

    public Query getEventsForGroup(String groupID){
        return mDatabase.getReference().child(SCHEDULES_CHILD).child(groupID).orderByChild(ORDER_BY_DATE);
    }

    public void addNewGroup(Chats chat){
        chat.people = new HashMap<String,String>();
        chat.members = 1;
        chat.people.put(mAuth.getCurrentUser().getUid(), mAuth.getCurrentUser().getDisplayName());
        String key = mDatabase.getReference().child(CHATS_CHILD).push().getKey();
        mDatabase.getReference().child(CHATS_CHILD).child(key).setValue(chat);
        mDatabase.getReference().child(USERS_CHILD).child(mAuth.getCurrentUser().getUid()).child(GROUPS_CHILD).child(key).setValue(true);
    }

    public  Query getGroups(){
        getInstanceDatabase();
        getInstanceAuth();
        return mDatabase.getReference().child(USERS_CHILD).child(getUser().getUid());
    }

    public FirebaseUser getUser(){
        getInstanceAuth();
        return mAuth.getCurrentUser();
    }

    public static FirebaseUtil getInstance(){
        if(firebaseUtil == null){
            firebaseUtil = new FirebaseUtil();
            firebaseUtil.getInstanceDatabase();
            firebaseUtil.getInstanceAuth();
        }
        return firebaseUtil;

    }

    public void updateGroup(Chats chats){
        mDatabase.getReference().child(CHATS_CHILD).child(chats.id).setValue(chats);
    }

    public void addUser(Chats chats, String newId, String name){
        mDatabase.getReference().child(USERS_CHILD).child(newId).child(GROUPS_CHILD).child(chats.id).setValue(true);
        mDatabase.getReference().child(CHATS_CHILD).child(chats.id).child(PEOPLE_CHILD).child(newId).setValue(name);
    }

    public Query getUserInfo(String email){
        String newEmail = email.replaceAll("\\.", "%2E");
        return mDatabase.getReference().child(USERNAME_CHILD).child(newEmail);
    }

    public void addUserInfo(String email){
        String newEmail = email.replaceAll("\\.", "%2E");
        mDatabase.getReference().child(USERNAME_CHILD).child(newEmail).child("id").setValue(mAuth.getCurrentUser().getUid());
        mDatabase.getReference().child(USERNAME_CHILD).child(newEmail).child("name").setValue(mAuth.getCurrentUser().getDisplayName());
    }

}
