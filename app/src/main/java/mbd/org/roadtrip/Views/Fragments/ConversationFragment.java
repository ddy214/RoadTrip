package mbd.org.roadtrip.Views.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.layer.atlas.AtlasMessagesRecyclerView;

import mbd.org.roadtrip.R;

public class ConversationFragment extends Fragment {


    public ConversationFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ConversationFragment newInstance(String param1, String param2) {
        ConversationFragment fragment = new ConversationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        AtlasMessagesRecyclerView messageList = ((AtlasMessagesRecyclerView) view.findViewById(R.id.messagesList));
        return view;
    }


}
