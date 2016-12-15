package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Groups;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.Views.Activities.MainActivity;
import mbd.org.roadtrip.Views.Adapters.GroupAdapter;

/**
 * Created by derekyu on 12/14/16.
 */

public class GroupListViewModel extends BaseObservable {
    private Context mContext;
    private GroupAdapter mAdapter;
    private ArrayList<Chats> mChats = new ArrayList<>();


    public GroupListViewModel(Context context, GroupAdapter adapter){
        mContext = context;
        mAdapter = adapter;
    }

    public void getGroups(){
        mChats = new ArrayList<>();
        FirebaseUtil.getInstance().getGroups().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Groups groups = dataSnapshot.getValue(Groups.class);
                if(groups != null) {
                    for (final String key : groups.groups.keySet()) {
                        FirebaseUtil.getInstance().getChats(key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Chats chats = dataSnapshot.getValue(Chats.class);
                                chats.id = key;
                                mChats.add(chats);
                                mAdapter.setmChats(mChats);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public View.OnClickListener goToTripCreator(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HandleTripButton) mContext).handleButtonClick();
            }
        };
    }

    public interface  HandleTripButton{
        public void handleButtonClick();

    }
}
