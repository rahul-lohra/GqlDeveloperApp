package com.rahullohra.gqldeveloperapp

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter (): RecyclerView.Adapter<VH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(TextView(parent.context))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }
}

class VH(itemView: View):RecyclerView.ViewHolder(itemView)