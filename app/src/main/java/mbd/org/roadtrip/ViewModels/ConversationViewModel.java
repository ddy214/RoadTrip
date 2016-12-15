package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.Views.Adapters.MessageAdapter;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;

/**
 * Created by derekyu on 11/20/16.
 */

public class ConversationViewModel {
    private Context mContext;
    private FragmentConversationBinding mBinding;
    private String groupID;
    private MessageAdapter mAdapter;

    public ConversationViewModel(Context context, FragmentConversationBinding binding, String groupID, MessageAdapter adapter){
        mContext = context;
        mBinding = binding;
        this.groupID = groupID;
        mAdapter = adapter;
    }

    public View.OnClickListener sendMessage(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBinding.input.getText().toString().isEmpty()){
                    return;
                } else {
                    String message = mBinding.input.getText().toString();
                    FirebaseUtil.getInstance().sendNewMessage(message, groupID);
                    ((LinearLayoutManager) mBinding.recyclerView.getLayoutManager()).setStackFromEnd(true);
                    mBinding.recyclerView.smoothScrollToPosition(mAdapter.getItemCount());
                    mBinding.input.setText("");
                }
            }
        };
    }




}
