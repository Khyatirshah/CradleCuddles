package com.cradlecuddles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cradlecuddles.MainActivity;
import com.cradlecuddles.R;
import com.cradlecuddles.Utils.Utils;
import com.cradlecuddles.foundation.BaseFragment;

/**
 * Created by Khyati Shah on 11/19/2018.
 */
public class ChildCareFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llVaccination;

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
        View view = inflater.inflate(R.layout.fragment_childcare, container, false);
        llVaccination = view.findViewById(R.id.llVaccination);
        llVaccination.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.llVaccination:
                Utils.addSubscreen(getActivity(), new VaccinationFragment());
                break;

        }
    }
}
