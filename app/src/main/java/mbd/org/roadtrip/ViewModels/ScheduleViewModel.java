package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import mbd.org.roadtrip.BR;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.Models.Schedule;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.Views.Activities.ScheduleActivity;
import mbd.org.roadtrip.Views.UI.DatePagerAdapter;
import mbd.org.roadtrip.Views.UI.SlidingTabLayout;
import mbd.org.roadtrip.databinding.ActivityScheduleBinding;

/**
 * Created by derekyu on 12/13/16.
 */

public class ScheduleViewModel extends BaseObservable {
    private Context mContext;
    private Schedule schedule = new Schedule();
    private String month;
    public DatePagerAdapter mPagerAdapter;
    private ActivityScheduleBinding mBinding;
    private Chats mChats;


    public ScheduleViewModel(Context context, ActivityScheduleBinding binding, Chats chats){
        mBinding = binding;
        mContext = context;
        mChats = chats;

    }

    public void getEvents(){
        FirebaseUtil.getInstance().getEventsForGroup(mChats.id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Schedule schedule = new Schedule();
                        for(DataSnapshot eventShot:dataSnapshot.getChildren()){
                            Event event = eventShot.getValue(Event.class);
                            schedule.events.add(event);
                            Log.d("event", "event " + event.name + " " +event.description + " " + event.date+" "+ event.location + " " + event.duration );
                        }
                        if(schedule.events != null) {
                            mPagerAdapter.setSchedule(schedule.events, getDays());
                        } else{
                            Log.d("null","null");
                        }
                        ViewPager viewPager = mBinding.viewPager;
                        if(viewPager != null) {
                            viewPager.setAdapter(mPagerAdapter);
                        } else {
                            Log.d("null","null");
                        }
                        mBinding.slidingTab.setViewPager(viewPager);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public long getStartDay(){
        return mChats.start;
    }

    public long getEndDay(){
        return mChats.end;
    }


    public long getDayToMilli(int day){
        return 1000 * 60 * 60 * 24 *day;
    }

    public int getMilliToDay(long milli){
        return Integer.parseInt((milli/(1000 * 60 * 60 * 24))+"");
    }

    public int getDays(){
        int days = getMilliToDay(mChats.end - mChats.start);
        if(days==0){
            return 1;
        } else {
            return days;
        }
    }

    public String getDay(int i){
        long day = getStartDay()+getDayToMilli(i);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(day);
        return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US)+ " " + cal.get(Calendar.DAY_OF_MONTH);
    }

    public void setMonth(int i){
        long day = getStartDay()+getDayToMilli(i);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(day);
        month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        notifyPropertyChanged(BR.month);
    }

    @Bindable
    public String getMonth(){
        return month;
    }
}

