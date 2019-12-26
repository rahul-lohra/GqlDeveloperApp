package com.rahullohra.fakeresponse.presentaiton.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.fakeresponse.R
import com.rahullohra.fakeresponse.ResponseListData

class ResponseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun getLayout() = R.layout.item_response
    }

    val tv: TextView = itemView.findViewById(R.id.tvQueryName)

    fun setData(data: ResponseListData) {
        tv.text = data.title
    }

}