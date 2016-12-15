package mbd.org.roadtrip.ViewModels;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import mbd.org.roadtrip.BR;
import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.Utils.DialogGenerator;
import mbd.org.roadtrip.Utils.FirebaseUtil;


/**
 * Created by derekyu on 12/12/16.
 */

public class DialogCEViewModel extends BaseObservable implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, DialogInterface.OnClickListener {
    private Context mContext;
    private Event mEvent;
    private Calendar eventTimeDay = Calendar.getInstance();
    private Chats mChat;

    public DialogCEViewModel(Context context, String description, Chats chat){
        mContext = context;
        mEvent = new Event();
        mEvent.description = description;
        mChat = chat;
        if(mChat != null){
            eventTimeDay.setTimeInMillis(mChat.start);
        }
    }


    public String getDescription(){
        return mEvent.description;
    }

    public String getName(){
        return mEvent.name;
    }

    public String getLocation(){
        return mEvent.location;
    }

    @Bindable
    public String getDate(){
        return (eventTimeDay.get(Calendar.MONTH)+1)
                +"/"+(eventTimeDay.get(Calendar.DAY_OF_MONTH)
                +"/"+eventTimeDay.get(Calendar.YEAR));
    }

    @Bindable
    public String getTime(){
        int isAfternoon = eventTimeDay.get(Calendar.AM_PM);
        int minutes = eventTimeDay.get(Calendar.MINUTE);
        String minutesString ="";
        if(minutes > 9){
            minutesString += minutes;
        } else {
            minutesString += ("0"+minutes);
        }
        if(isAfternoon == 1){
            return eventTimeDay.get(Calendar.HOUR)+":"+ minutesString + " PM";
        }
        else {
            return eventTimeDay.get(Calendar.HOUR)+":"+ minutesString + " AM";
        }
    }


    private TimePickerDialog.OnTimeSetListener getTimeListener(){
        return this;
    }
    private DatePickerDialog.OnDateSetListener getDateListener(){
        return this;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        eventTimeDay.set(Calendar.YEAR, year);
        eventTimeDay.set(Calendar.MONTH, month);
        eventTimeDay.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        notifyPropertyChanged(BR.date);
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        eventTimeDay.set(Calendar.HOUR_OF_DAY, hourOfDay);
        eventTimeDay.set(Calendar.MINUTE, minute);
        notifyPropertyChanged(BR.time);
    }


    public View.OnClickListener chooseDate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createDateDialog(mContext, getDateListener(), mChat).show();
            }
        };
    }

    public View.OnClickListener chooseTime(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenerator.createTimeDialog(mContext, getTimeListener()).show();
            }
        };
    }

    public void onNameChange(CharSequence s, int start, int before, int count){
        mEvent.name = s.toString();
    }

    public void onDescriptionChange(CharSequence s, int start, int before, int count){
        mEvent.description = s.toString();
    }

    public void onLocationChange(CharSequence s, int start, int before, int count){
        mEvent.location = s.toString();
    }

    public void onDurationChange(CharSequence s, int start, int before, int count){
        try {
            mEvent.duration = Float.parseFloat(s.toString());
        } catch(Exception e){
            mEvent.duration=0;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mEvent.date = eventTimeDay.getTimeInMillis();
        FirebaseUtil.getInstance().createNewEvent(mEvent, mChat.id);
        Toast.makeText(mContext, "Event Created", Toast.LENGTH_SHORT).show();
    }
}


