package com.cradlecuddles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cradlecuddles.MainActivity;
import com.cradlecuddles.R;
import com.cradlecuddles.foundation.BaseFragment;

/**
 * Created by Khyati Shah on 11/19/2018.
 */
public class ChildCareFragment extends BaseFragment {

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.ChildCare));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_childcare, container, false);
    }
}
