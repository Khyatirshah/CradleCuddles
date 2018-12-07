package com.cradlecuddles.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.MainActivity
import com.cradlecuddles.R
import com.cradlecuddles.adapters.childcare_vaccination.VaccinationAdapter
import com.cradlecuddles.foundation.BaseFragment
import com.cradlecuddles.models.Vaccination
import kotlinx.android.synthetic.main.fragment_vaccination.*

/**
 * Created by Khyati Shah on 12/3/2018.
 */
class VaccinationFragment : BaseFragment() {
    private var btnShowVaccinations: Button? = null
    private var spinnerAge: Spinner? = null
    private var vaccinations: ArrayList<Vaccination> = ArrayList()
    private var vaccAdapter: VaccinationAdapter? = null

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

        btnShowVaccinations!!.setOnClickListener {
            if (spinnerAge!!.selectedItem.toString().equals("Select child's age")) {
                Toast.makeText(activity, "Select Age", Toast.LENGTH_LONG).show()

            } else {
                vaccinations.clear()
                var tempVaccinations = CradleCuddles.dataBaseHelper?.getVaccinations(spinnerAge!!.selectedItem.toString())
                if (tempVaccinations != null) {
                    vaccinations.addAll(tempVaccinations)
                    vaccAdapter!!.notifyDataSetChanged()
                }
            }
        }
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lstVaccination.layoutManager = LinearLayoutManager(activity)
        vaccAdapter = VaccinationAdapter(vaccinations)
        lstVaccination.adapter = vaccAdapter
    }
}