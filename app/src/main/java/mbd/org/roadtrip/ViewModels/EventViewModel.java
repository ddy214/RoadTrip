package mbd.org.roadtrip.ViewModels;

import android.content.Context;

import java.util.Calendar;

import mbd.org.roadtrip.Models.Event;

/**
 * Created by derekyu on 12/14/16.
 */

public class EventViewModel {

    private Event mEvent;
    private Context mContext;

    public EventViewModel(Context context, Event event){
        mEvent = event;
        mContext = context;
    }

    public String getName(){
        return mEvent.name;
    }

    public String getLocation(){
        return mEvent.location;
    }

    public String getDescription(){
        return mEvent.description;
    }

    public String getDuration(){
        return mEvent.duration+" hr";
    }
    public String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mEvent.date);
        int isAfternoon = cal.get(Calendar.AM_PM);
        int minutes = cal.get(Calendar.MINUTE);
        String minutesString = "";
        if (minutes > 9) {
            minutesString += minutes;
        } else {
            minutesString += ("0" + minutes);
        }
        if (isAfternoon == 1) {
            return cal.get(Calendar.HOUR) + ":" + minutesString + " PM";
        } else {
            return cal.get(Calendar.HOUR) + ":" + minutesString + " AM";
        }
    }
}
