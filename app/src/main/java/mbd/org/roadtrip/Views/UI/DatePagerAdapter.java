package mbd.org.roadtrip.Views.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.ViewModels.ScheduleViewModel;
import mbd.org.roadtrip.Views.Fragments.ScheduleFragment;

/**
 * Created by derekyu on 12/14/16.
 */

public class DatePagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    ArrayList<Event> mEvents = new ArrayList<>();
    ScheduleViewModel viewModel;
    Chats mChat;
    int mDays=0;

    public DatePagerAdapter(FragmentManager fm, Context context, ScheduleViewModel scheduleViewModel, Chats chats) {
        super(fm);
        mContext = context;
        viewModel = scheduleViewModel;
        mChat = chats;

    }

    public void setSchedule(ArrayList<Event> events, int days){
        mEvents.clear();
        mEvents.addAll(events);
        mDays = days;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDays;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleFragment.newInstance(mEvents, position, mChat);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return viewModel.getDay(position);
    }


}