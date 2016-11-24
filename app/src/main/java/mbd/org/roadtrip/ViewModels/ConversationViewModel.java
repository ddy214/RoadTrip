package mbd.org.roadtrip.ViewModels;

import android.content.Context;
import android.view.View;

import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;

/**
 * Created by derekyu on 11/20/16.
 */

public class ConversationViewModel {
    private Context mContext;
    private FragmentConversationBinding mBinding;
    private String groupID;

    public ConversationViewModel(Context context, FragmentConversationBinding binding, String groupID){
        mContext = context;
        mBinding = binding;
        this.groupID = groupID;
    }

    public View.OnClickListener sendMessage(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBinding.input.getText().toString().isEmpty()){
                    return;
                } else {
                    String message = mBinding.input.getText().toString();
                    FirebaseUtil.sendNewMessage(message, groupID);
                }
            }
        };
    }


}
