package mbd.org.roadtrip.Views.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mbd.org.roadtrip.Models.Message;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.ViewModels.ConversationViewModel;
import mbd.org.roadtrip.Views.Adapters.MessageAdapter;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;

public class ConversationFragment extends Fragment {
    private ConversationViewModel viewModel;
    private FirebaseRecyclerAdapter recyclerAdapter;
    private String MESSAGE_CHILD_ONE = "one";
    private FragmentConversationBinding binding;

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
        recyclerAdapter = new MessageAdapter(R.layout.message_left, FirebaseUtil.getInstanceDatabase().child("messages").child("one").orderByChild("timestamp"), FirebaseAuth.getInstance().getCurrentUser().getEmail(), getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_conversation, container, false);
        viewModel = new ConversationViewModel(getContext(), binding, MESSAGE_CHILD_ONE);
        binding.setViewModel(viewModel);
        RecyclerView recycleView =  binding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setAdapter(recyclerAdapter);
        return binding.getRoot();
    }


}
