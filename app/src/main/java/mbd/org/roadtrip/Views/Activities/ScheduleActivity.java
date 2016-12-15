package mbd.org.roadtrip.Views.Activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Views.UI.DatePagerAdapter;
import mbd.org.roadtrip.Models.Schedule;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.ScheduleViewModel;
import mbd.org.roadtrip.Views.Fragments.ScheduleFragment;
import mbd.org.roadtrip.Views.UI.SlidingTabLayout;
import mbd.org.roadtrip.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private DatePagerAdapter datePager;
    private ActivityScheduleBinding binding;
    private SlidingTabLayout slidingTabLayout;
    private ScheduleViewModel viewModel;
    private Chats mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = getIntent().getParcelableExtra("chat");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schedule);
        viewModel = new ScheduleViewModel(this, binding, mChat);
        setupPager();
        viewModel.mPagerAdapter = datePager;
        binding.setViewModel(viewModel);
        setupTab();
        setupActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getEvents();
    }

    public void setupActionBar(){
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
    }

    public void setupPager(){
        mViewPager = binding.viewPager;
        datePager = new DatePagerAdapter(getSupportFragmentManager(), this, viewModel, mChat);
        mViewPager.setAdapter(datePager);
        viewModel.setMonth(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewModel.setMonth(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void setupTab(){
        slidingTabLayout = binding.slidingTab;
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.schedule_menu, menu);
        return true;
    }

    public static Intent getScheduleActivity(Context context, Chats chats){
        Intent intent = new Intent(context, ScheduleActivity.class);
        intent.putExtra("chat",chats);
        return intent;
    }

}
