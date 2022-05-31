package com.pcl.myapplication.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pcl.myapplication.R

class ItemCheckViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val ivCheck: ImageView = view.findViewById(R.id.ivCheck)

    val tvDeptName: TextView = view.findViewById(R.id.tvDeptName)

    val btnExpand: TextView = view.findViewById(R.id.btnExpand)

    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

}