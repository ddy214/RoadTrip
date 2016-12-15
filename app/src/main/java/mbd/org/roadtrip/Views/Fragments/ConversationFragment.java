package mbd.org.roadtrip.Views.Fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.Models.Message;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.Utils.FirebaseUtil;
import mbd.org.roadtrip.ViewModels.ConversationViewModel;
import mbd.org.roadtrip.Views.Adapters.MessageAdapter;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;

public class ConversationFragment extends Fragment {
    private ConversationViewModel viewModel;
    private MessageAdapter recyclerAdapter;
    private String MESSAGE_CHILD_ONE = "one";
    private FragmentConversationBinding binding;
    private Chats chats;

    public ConversationFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public static ConversationFragment getInstance(Chats chats) {
        Bundle args = new Bundle();
        args.putParcelable("chat", chats);
        ConversationFragment fragment = new ConversationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chats = getArguments().getParcelable("chat");
        recyclerAdapter = new MessageAdapter(R.layout.message_left, FirebaseUtil.getInstance().getMessagesForGroup(chats.id), FirebaseAuth.getInstance().getCurrentUser().getEmail(), getContext(), chats);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_conversation, container, false);
        viewModel = new ConversationViewModel(getContext(), binding, chats.id, recyclerAdapter);
        binding.setViewModel(viewModel);
        final RecyclerView recycleView =  binding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recycleView.setLayoutManager(linearLayoutManager);
        recycleView.setScrollContainer(true);
        recycleView.setAdapter(recyclerAdapter);
        setupToolBar();
        return binding.getRoot();
    }

    private void setupToolBar(){
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

}
