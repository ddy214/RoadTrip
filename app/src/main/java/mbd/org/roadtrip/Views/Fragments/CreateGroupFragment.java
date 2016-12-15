package mbd.org.roadtrip.Views.Fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mbd.org.roadtrip.R;
import mbd.org.roadtrip.Utils.DialogGenerator;
import mbd.org.roadtrip.ViewModels.CreateGroupViewModel;
import mbd.org.roadtrip.ViewModels.GroupListViewModel;
import mbd.org.roadtrip.databinding.FragmentConversationBinding;
import mbd.org.roadtrip.databinding.FragmentCreateGroupBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateGroupFragment extends Fragment {
    CreateGroupViewModel viewModel;
    FragmentCreateGroupBinding binding;

    public CreateGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_create_group, container, false);
        viewModel = new CreateGroupViewModel(getContext(), binding);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }



}
