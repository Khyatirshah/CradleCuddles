package com.cradlecuddles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.foundation.BaseFragment

/**
 * Created by Khyati Shah on 11/27/2018.
 */
class ExtrasFragment : BaseFragment() {

    override fun onResume() {
        super.onResume()
        // Set title bar
        (activity as MainActivity)
                .setActionBarTitle(getString(R.string.Extras))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_extras, container, false)
    }
}
