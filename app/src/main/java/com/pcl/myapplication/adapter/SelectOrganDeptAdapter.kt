package com.pcl.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pcl.myapplication.R
import com.pcl.myapplication.bean.Depts
import com.pcl.myapplication.utils.CollectionUtils
import com.pcl.myapplication.utils.DpUtils

class SelectOrganDeptAdapter(
    private val context: Context,
    private val allDataList: List<Depts.Children>?,
    private val currentDataList: List<Depts.Children>?
) :
    RecyclerView.Adapter<ItemCheckViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCheckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_select_depts, parent, false)
        return ItemCheckViewHolder(view)
    }

    override fun getItemCount(): Int = currentDataList?.size ?: 0

    override fun getItemViewType(position: Int): Int =
        if (currentDataList == null) 0 else currentDataList[position].id

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ItemCheckViewHolder, position: Int) {
        val item = currentDataList?.get(position)

        item?.let {
            with(holder) {

                //勾选按钮      隐藏显示
                if (item.isCanCheck) {
                    ivCheck.visibility = View.VISIBLE
                } else {
                    ivCheck.visibility = View.GONE
                }

                //展开收起按钮    隐藏显示
                if (item.isCanExpand) {
                    btnExpand.visibility = View.VISIBLE
                } else {
                    btnExpand.visibility = View.GONE
                }

                //银行名称
                tvDeptName.text = item.name

                when (item.depth) {
                    2 -> {
                        //二级行
                        tvDeptName.setTextColor(Color.parseColor("#FF0000"))
                        tvDeptName.setPadding(
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15)
                        )
                    }
                    3 -> {
                        //三级行
                        tvDeptName.setTextColor(Color.parseColor("#00FF00"))
                        tvDeptName.setPadding(
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15)
                        )
                    }
                    4 -> {
                        //四级行
                        tvDeptName.setTextColor(Color.parseColor("#0000FF"))
                        tvDeptName.setPadding(
                            DpUtils.dp2px(context,30),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                        )
                    }
                    else -> {
                        tvDeptName.setTextColor(Color.parseColor("#B8860B"))
                        tvDeptName.setPadding(
                            DpUtils.dp2px(context,45),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                            DpUtils.dp2px(context,15),
                        )
                    }
                }

                recyclerView.layoutManager = LinearLayoutManager(context)
                val adapter = SelectOrganDeptAdapter(context, allDataList, item.children)
                adapter.setOnCheckedListener {
                    notifyDataSetChanged()
                    listener?.invoke()
                }
                recyclerView.adapter = adapter

                if (item.isChecked) {
                    ivCheck.setImageResource(R.mipmap.icon_checked)
                } else {
                    ivCheck.setImageResource(R.mipmap.icon_un_checked)
                }

                if (!item.isExpand) {
                    recyclerView.visibility = View.GONE
                    val drawable: Drawable = context.resources.getDrawable(R.mipmap.icon_arrow_down)
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    btnExpand.setCompoundDrawables(null, null, drawable, null)
                } else {
                    recyclerView.visibility = View.VISIBLE
                    val drawable: Drawable = context.resources.getDrawable(R.mipmap.icon_arrow_up)
                    drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
                    btnExpand.setCompoundDrawables(null, null, drawable, null)
                }

                btnExpand.setOnClickListener {
                    if (item.isExpand) {
                        item.isExpand = false
                        notifyItemChanged(adapterPosition)
                    } else {
                        item.isExpand = true
                        notifyItemChanged(adapterPosition)
                    }
                }

                itemView.setOnClickListener {
                    if (item.depth != 2) {
                        uncheckAll(allDataList)
                        item.isChecked = true
                        notifyDataSetChanged()
                        listener?.invoke()
                    }
                }
            }
        }
    }

    /**
     * 取消选中所有
     */
    private fun uncheckAll(list: List<Depts.Children>?) {
        list?.forEach {
            it.isChecked = false
            if (CollectionUtils.isNotEmpty(it.children)) {
                uncheckAll(it.children)
            }
        }
    }


    private var listener: (() -> Unit)? = null

    fun setOnCheckedListener(listener: (() -> Unit)?) {
        this.listener = listener
    }


}