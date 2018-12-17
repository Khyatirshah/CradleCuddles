package com.cradlecuddles.adapters.childcare_vaccination

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.cradlecuddles.CradleCuddles
import com.cradlecuddles.R
import com.cradlecuddles.models.Vaccination
import kotlinx.android.synthetic.main.item_vaccination.view.*
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.provider.CalendarContract


/**
 * Created by Khyati Shah on 12/4/2018.
 */

class VaccinationAdapter(private val vaccinations: ArrayList<Vaccination>) : RecyclerView.Adapter<VaccinationAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            holder.valVaccinationName.text = vaccinations[position].vaccName
            holder.valContentTag.text = vaccinations[position].contentTag
            holder.valDose.text = vaccinations[position].doses.toString()
            holder.valApproxPrice.text = vaccinations[position].approxPrice
            if (vaccinations[position].isDone == 0) {
                holder.imgDone.visibility = View.GONE
                holder.imgReminder.visibility = View.VISIBLE
                holder.switchDone.isChecked = false
            } else {
                holder.imgDone.visibility = View.VISIBLE
                holder.imgReminder.visibility = View.GONE
                holder.switchDone.isChecked = true
            }
            holder.switchDone.setOnCheckedChangeListener({ _, isChecked ->


            })
            holder.switchDone.setOnClickListener {
                if (holder.switchDone.isChecked) {
                    holder.imgDone.visibility = View.VISIBLE
                    holder.imgReminder.visibility = View.GONE
                    CradleCuddles.dataBaseHelper?.updateIsDone(vaccinations[position].vaccName, 1)
                    vaccinations[position].isDone = 1
                } else {
                    holder.imgDone.visibility = View.GONE
                    holder.imgReminder.visibility = View.VISIBLE
                    CradleCuddles.dataBaseHelper?.updateIsDone(vaccinations[position].vaccName, 0)
                    vaccinations[position].isDone = 0
                }
                notifyDataSetChanged()
            }

            holder.imgReminder.setOnClickListener {

                val intent = Intent(Intent.ACTION_EDIT)
                intent.type = "vnd.android.cursor.item/event"
                intent.putExtra("allDay", true)
                intent.putExtra(CalendarContract.Events.TITLE, vaccinations[position].vaccName)
                context?.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return vaccinations.size
    }


    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VaccinationAdapter.ViewHolder {
        context = parent?.context
        return VaccinationAdapter.ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_vaccination, parent, false))

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val valVaccinationName = itemView.valVaccinationName
        val valContentTag = itemView.valContentTag
        val valDose = itemView.valDose
        val valApproxPrice = itemView.valApproxPrice
        val imgReminder = itemView.imgReminder
        val switchDone = itemView.switchDone
        val imgDone = itemView.imgDone
    }
}