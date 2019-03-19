package com.cradlecuddles.adapters.childcare_diet

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cradlecuddles.R
import java.util.ArrayList

class ChildCareDietAdapter(val arrAge: ArrayList<String>) : RecyclerView.Adapter<ChildCareDietAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return arrAge.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            holder.txtAge.text = arrAge.get(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChildCareDietAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_age_diet, parent, false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtAge = view.findViewById<TextView>(R.id.txtAge)
    }
}