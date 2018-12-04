package com.cradlecuddles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.Utils.Utils
import com.cradlecuddles.foundation.BaseFragment

/**
 * Created by Khyati Shah on 11/19/2018.
 */
class ChildCareFragment : BaseFragment(), View.OnClickListener {

    private var llVaccination: LinearLayout? = null

    override fun onResume() {
        super.onResume()
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.ChildCare))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_childcare, container, false)
        llVaccination = view.findViewById(R.id.llVaccination)
        llVaccination!!.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View) {
        when (view.id) {

            R.id.llVaccination -> Utils.addSubscreen(activity, VaccinationFragment())
        }
    }
}
