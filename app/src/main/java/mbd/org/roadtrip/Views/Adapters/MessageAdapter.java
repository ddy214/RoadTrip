package mbd.org.roadtrip.Views.Adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.Binding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import mbd.org.roadtrip.BR;
import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Message;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.MessageViewModel;

/**
 * Created by derekyu on 11/21/16.
 */

public class MessageAdapter extends FirebaseRecyclerAdapter<Message,MessageAdapter.BindingHolder> {

    private final int RIGHT_TEXT = 0;
    private final int LEFT_TEXT = 1;

    private Context mContext;
    private String mEmail;
    private Chats mChats;

    public MessageAdapter(int modelLayout, Query ref, String id, Context context, Chats chats) {
        super(Message.class, modelLayout, BindingHolder.class, ref);
        mContext = context;
        mEmail = id;
        mChats = chats;
    }


    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = null;
        switch (viewType){
            case RIGHT_TEXT:
                dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.message_right, parent, false);
                break;

            case LEFT_TEXT:
                dataBinding =  DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.message_left, parent, false);
                break;
        }
        return new BindingHolder(dataBinding);

    }


    @Override
    public int getItemViewType(int position) {
        Log.d(getItem(position).email,"");
        if(getItem(position).email.equals(mEmail)){
            return RIGHT_TEXT;
        }
        return LEFT_TEXT;
    }

    @Override
    protected void populateViewHolder(BindingHolder viewHolder, Message model, int position) {
        viewHolder.binding.setVariable(BR.message, new MessageViewModel(mContext, model, mChats));
    }

    public static class BindingHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        public BindingHolder(ViewDataBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}

