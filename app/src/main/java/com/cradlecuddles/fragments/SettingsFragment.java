package com.cradlecuddles.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.cradlecuddles.MainActivity;
import com.cradlecuddles.R;
import com.cradlecuddles.Utils.Constants;
import com.cradlecuddles.Utils.LanguageHelper;
import com.cradlecuddles.Utils.Utils;
import com.cradlecuddles.foundation.BaseFragment;

/**
 * Created by Khyati Shah on 11/27/2018.
 */
public class SettingsFragment extends BaseFragment {

    private LinearLayout llLanguage;

    @Override
    public void onResume() {
        super.onResume();
        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.Settings));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        llLanguage = view.findViewById(R.id.llLanguage);

        llLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create a Dialog component
                final Dialog dialog = new Dialog(getActivity());

                //tell the Dialog to use the dialog.xml as it's layout description
                dialog.setContentView(R.layout.dialog_language);
                Button btnOk = dialog.findViewById(R.id.btnOk);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                final RadioGroup myRadioGroup = dialog.findViewById(R.id.myRadioGroup);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (myRadioGroup.getCheckedRadioButtonId()) {
                            case R.id.english:
                                LanguageHelper.setLanguage(getActivity(), Constants.ENG);
                                break;
                            case R.id.hindi:
                                LanguageHelper.setLanguage(getActivity(), Constants.HINDI);
                                break;
                        }
                        getActivity().finish();
                        startActivity(new Intent(getContext(), MainActivity.class));
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }
}

