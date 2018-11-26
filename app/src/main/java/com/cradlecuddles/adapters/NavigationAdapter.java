package com.cradlecuddles.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cradlecuddles.R;
import com.cradlecuddles.models.NavigationItem;

import java.util.ArrayList;

/**
 * Created by Khyati Shah on 11/26/2018.
 */
public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.MyViewHolder> {

    ArrayList<NavigationItem> navigationItems;

    public NavigationAdapter(ArrayList<NavigationItem> navigationItems) {
        this.navigationItems = navigationItems;
    }

    @Override
    public NavigationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drawer_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NavigationAdapter.MyViewHolder holder, int position) {
        holder.txtTitle.setText(navigationItems.get(position).getStrTitle());
    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }
}
