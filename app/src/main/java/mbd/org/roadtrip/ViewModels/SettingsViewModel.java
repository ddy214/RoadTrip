package mbd.org.roadtrip.ViewModels;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import mbd.org.roadtrip.BR;
import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.User;
import mbd.org.roadtrip.Utils.DialogGenerator;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.Views.Fragments.SettingsFragment;
import mbd.org.roadtrip.databinding.FragmentCreateGroupBinding;
import mbd.org.roadtrip.databinding.FragmentSettingsBinding;

/**
 * Created by derekyu on 12/15/16.
 */

public class SettingsViewModel extends BaseObservable implements DatePickerDialog.OnDateSetListener {
    private Context mContext;
    private Chats mChat;
    private Calendar tripStart = Calendar.getInstance();
    private Calendar tripEnd = Calendar.getInstance();
    private FragmentSettingsBinding mBinding;

    public SettingsViewModel(Context context, Chats chats, FragmentSettingsBinding binding){
        mChat = chats;
        mContext = context;
        mBinding = binding;
        tripStart.setTimeInMillis(chats.start);
        tripEnd.setTimeInMillis(chats.end);
    }

    public String getTitle(){
        return mChat.title;
    }

    public String getLocation(){
        return mChat.destination;
    }


    private DatePickerDialog.OnDateSetListener getDateListener(){
        return this;
    }

    private DatePickerDialog.OnDateSetListener getDateEndListener(final Calendar endTrip){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endTrip.set(Calendar.YEAR, year);
                endTrip.set(Calendar.MONTH, month);
                endTrip.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                notifyPropertyChanged(BR.endDate);
            }
        };
    }


    public View.OnClickListener chooseDate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createDateDialog(mContext, getDateListener(), mChat).show();
            }
        };
    }

    public View.OnClickListener chooseEndDate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createDateDialog(mContext, getDateEndListener(tripEnd), mChat).show();
            }
        };
    }


    @Bindable
    public String getDate(){
        return (tripStart.get(Calendar.MONTH)+1)
                +"/"+(tripStart.get(Calendar.DAY_OF_MONTH)
                +"/"+tripStart.get(Calendar.YEAR));
    }

    @Bindable
    public String getEndDate(){
        return (tripEnd.get(Calendar.MONTH)+1)
                +"/"+(tripEnd.get(Calendar.DAY_OF_MONTH)
                +"/"+tripEnd.get(Calendar.YEAR));
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        tripStart.set(Calendar.YEAR, year);
        tripStart.set(Calendar.MONTH, month);
        tripStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        notifyPropertyChanged(BR.date);
    }

    public View.OnClickListener editGroup(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mBinding.name.getText().toString();
                String destination = mBinding.location.getText().toString();
                Log.d("group", name + " " + destination + " " + tripStart + " " +  mChat.start );
                mChat.title = name;
                mChat.destination = destination;
                mChat.start = tripStart.getTimeInMillis();
                mChat.end = tripEnd.getTimeInMillis();
                FirebaseUtil.getInstance().updateGroup(mChat);
                Toast.makeText(mContext, "Updated Trip", Toast.LENGTH_SHORT).show();
            }
        };
    }


    public View.OnClickListener addFriend(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mBinding.email.getText().toString();
                FirebaseUtil.getInstance().getUserInfo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        if(user == null){
                            Toast.makeText(mContext, "User Does Not Exist", Toast.LENGTH_SHORT).show();
                        } else {
                            FirebaseUtil.getInstance().addUser(mChat, user.id, user.name);
                            Toast.makeText(mContext, "User Added to " + mChat.title, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("cancelled", "adfa");
                    }
                });

            }
        };
    }
}
