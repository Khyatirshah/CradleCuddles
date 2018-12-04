package com.cradlecuddles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner

import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.foundation.BaseFragment

/**
 * Created by Khyati Shah on 12/3/2018.
 */
class VaccinationFragment : BaseFragment() {
    private var btnShowVaccinations: Button? = null
    private var spinnerAge: Spinner? = null

    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.Vaccination))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_vaccination, container, false)
        btnShowVaccinations = view.findViewById(R.id.btnShowVaccinations)
        spinnerAge = view.findViewById(R.id.spinnerAge)
        btnShowVaccinations!!.setOnClickListener { CradleCuddles.dataBaseHelper?.getVaccinations(spinnerAge!!.selectedItem.toString()) }
        return view
    }
}