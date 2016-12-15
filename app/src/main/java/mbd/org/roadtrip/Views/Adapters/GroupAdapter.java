package mbd.org.roadtrip.Views.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.GroupViewModel;
import mbd.org.roadtrip.databinding.GroupLayoutBinding;

/**
 * Created by derekyu on 12/14/16.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.BindingHolder> {
    Context mContext;
    List<Chats> mChats = new ArrayList<Chats>();

    public GroupAdapter(Context context){
        mContext = context;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GroupLayoutBinding groupLayoutBinding;
        groupLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.group_layout, parent, false);
        return new BindingHolder(groupLayoutBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        GroupLayoutBinding groupItemBinding = holder.binding;
        groupItemBinding.setGroupViewModel(new GroupViewModel(mContext, mChats.get(position)));
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }

    public void setmChats(List<Chats> chats) {
        Log.d("chats", chats.size()+"");
        mChats = chats;
        Log.d("chats", chats.size()+"");
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{
        private GroupLayoutBinding binding;

        public BindingHolder(GroupLayoutBinding binding){
            super(binding.cardView);
            this.binding = binding;
        }
    }

}
