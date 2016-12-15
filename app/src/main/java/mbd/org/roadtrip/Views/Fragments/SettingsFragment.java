package mbd.org.roadtrip.Views.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.CreateGroupViewModel;
import mbd.org.roadtrip.ViewModels.SettingsViewModel;
import mbd.org.roadtrip.databinding.FragmentCreateGroupBinding;
import mbd.org.roadtrip.databinding.FragmentSettingsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    SettingsViewModel viewModel;
    FragmentSettingsBinding binding;
    private Chats mChat;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChat = getArguments().getParcelable("chat");

    }

    public static SettingsFragment getInstance(Chats chat){
        Bundle args = new Bundle();
        args.putParcelable("chat", chat);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_settings, container, false);
        viewModel = new SettingsViewModel(getContext(), mChat, binding);
        binding.setViewModel(viewModel);
        setupToolBar();
        return binding.getRoot();
    }

    private void setupToolBar(){
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }


}