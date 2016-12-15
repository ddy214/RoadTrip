package mbd.org.roadtrip.Views.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mbd.org.roadtrip.Models.Chats;
import mbd.org.roadtrip.R;
import mbd.org.roadtrip.ViewModels.GroupListViewModel;
import mbd.org.roadtrip.Views.Adapters.GroupAdapter;
import mbd.org.roadtrip.databinding.FragmentGroupsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {
    private GroupListViewModel viewModel;
    private FragmentGroupsBinding binding;
    private GroupAdapter groupAdapter;

    public GroupsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupAdapter = new GroupAdapter(getContext());
        viewModel = new GroupListViewModel(getContext(), groupAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_groups, container, false);
        View view = binding.getRoot();
        binding.setGroupListViewModel(viewModel);
        setupRecycleView();
        setupToolBar();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getGroups();
    }

    private void setupRecycleView(){
        binding.chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.chatsRecyclerView.setAdapter(groupAdapter);

    }

    private void setupToolBar(){
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

}
