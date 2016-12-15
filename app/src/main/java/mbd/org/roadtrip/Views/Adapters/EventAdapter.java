package mbd.org.roadtrip.Views.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Event;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.EventViewModel;
import mbd.org.roadtrip.ViewModels.GroupViewModel;
import mbd.org.roadtrip.databinding.EventItemBinding;
import mbd.org.roadtrip.databinding.GroupLayoutBinding;

/**
 * Created by derekyu on 12/14/16.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.BindingHolder> {
    Context mContext;
    List<Event> mEvents = new ArrayList<Event>();

    public EventAdapter(Context context){
        mContext = context;
    }

    @Override
    public EventAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EventItemBinding eventItemBinding;
        eventItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.event_item, parent, false);
        return new EventAdapter.BindingHolder(eventItemBinding);
    }

    @Override
    public void onBindViewHolder(EventAdapter.BindingHolder holder, int position) {
        EventItemBinding eventItemBinding = holder.binding;
        eventItemBinding.setViewModel(new EventViewModel(mContext, mEvents.get(position)));
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void setmChats(List<Event> events) {
        mEvents = events;
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{
        private EventItemBinding binding;

        public BindingHolder(EventItemBinding binding){
            super(binding.cardView);
            this.binding = binding;
        }
    }

}
