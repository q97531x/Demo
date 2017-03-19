package com.example.component.demo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.component.demo.databinding.FragmentSimpleBinding;

/**
 * Created by weeboos on 2017/3/19.
 */

public class SimpleFragment extends Fragment {
    FragmentSimpleBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_simple,container,false);
        return binding.getRoot();
    }
}
