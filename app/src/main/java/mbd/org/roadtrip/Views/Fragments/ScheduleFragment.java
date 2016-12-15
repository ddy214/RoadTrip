package mbd.org.roadtrip.Views.Fragments;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.EventListViewModel;
import mbd.org.roadtrip.Views.Activities.MenuActivity;
import mbd.org.roadtrip.Views.Activities.ScheduleActivity;
import mbd.org.roadtrip.Views.Adapters.EventAdapter;
import mbd.org.roadtrip.Views.Adapters.GroupAdapter;
import mbd.org.roadtrip.databinding.FragmentScheduleBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private List<Event> events;
    private int day;
    private FragmentScheduleBinding binding;
    private EventAdapter eventAdapter;
    private Chats mChat;


    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance(ArrayList<Event>schedule, int day, Chats chats){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("schedule",schedule);
        args.putParcelable("chat", chats);
        args.putInt("day", day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventAdapter = new EventAdapter(getContext());
        events = getArguments().getParcelableArrayList("schedule");
        mChat = getArguments().getParcelable("chat");
        day = getArguments().getInt("day");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mChat.start + (1000 * 60 * 60 * 24 *day));
        ArrayList<Event>newArray = new ArrayList<>();
        for(int i = 0; i < events.size(); i++){
            Long val = events.get(i).date;
            int day = Integer.parseInt(((val-cal.getTimeInMillis())/(1000 * 60 * 60 * 24))+"");
            if(day == 0 ){
                newArray.add(events.get(i));
            }
        }
        events = newArray;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_schedule, container, false);
        binding.setViewModel(new EventListViewModel(getContext()));
        setupRecycleView();
        return binding.getRoot();
    }

    private void setupRecycleView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventAdapter.setmChats(events);
        binding.recyclerView.setAdapter(eventAdapter);

    }

}
