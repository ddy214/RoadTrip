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
import mbd.org.roadtrip.ViewModels.MenuViewModel;
import mbd.org.roadtrip.databinding.FragmentMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private MenuViewModel viewModel;


    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment getInstance(Chats chats){
        MenuFragment fragment = new MenuFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("chat", chats);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Chats chat = getArguments().getParcelable("chat");
        viewModel = new MenuViewModel(getContext(), chat);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_menu, container, false);
        binding.setViewModel(viewModel);
        setupToolBar();
        return binding.getRoot();

    }

    private void setupToolBar(){
        Toolbar toolbar = binding.toolbar;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

}
