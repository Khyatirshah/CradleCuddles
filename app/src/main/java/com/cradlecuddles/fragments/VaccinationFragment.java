package com.cradlecuddles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.cradlecuddles.CradleCuddles;
import com.cradlecuddles.MainActivity;
import com.cradlecuddles.R;
import com.cradlecuddles.foundation.BaseFragment;

/**
 * Created by Khyati Shah on 12/3/2018.
 */
public class VaccinationFragment extends BaseFragment {
    private Button btnShowVaccinations;
    private Spinner spinnerAge;

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.Vaccination));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccination, container, false);
        btnShowVaccinations = view.findViewById(R.id.btnShowVaccinations);
        spinnerAge = view.findViewById(R.id.spinnerAge);
        btnShowVaccinations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CradleCuddles.dataBaseHelper.getVaccinations(spinnerAge.getSelectedItem().toString());
            }
        });
        return view;
    }
}