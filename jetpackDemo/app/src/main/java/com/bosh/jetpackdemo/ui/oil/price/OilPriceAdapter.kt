package com.bosh.jetpackdemo.ui.oil.price

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.entity.OilPrice

/**
 * @author lzq
 * @date  2022/3/7
 */
class OilPriceAdapter : PagingDataAdapter<OilPrice, OilPriceViewHolder>(diffCallBack) {
    override fun onBindViewHolder(holder: OilPriceViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OilPriceViewHolder {
        return OilPriceViewHolder.create(parent)
    }

    companion object {
        val diffCallBack: DiffUtil.ItemCallback<OilPrice> = object :
            DiffUtil.ItemCallback<OilPrice>() {
            override fun areItemsTheSame(oldItem: OilPrice, newItem: OilPrice): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: OilPrice, newItem: OilPrice): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class OilPriceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvProvince: TextView = view.findViewById(R.id.tv_province)
    private val tvNinetyTwo: TextView = view.findViewById(R.id.tv_ninety_two)
    private val tvNinetyFive: TextView = view.findViewById(R.id.tv_ninety_five)
    private val tvNinetyEight: TextView = view.findViewById(R.id.tv_ninety_eight)
    private val tvZero: TextView = view.findViewById(R.id.tv_zero)
    private val tvTime: TextView = view.findViewById(R.id.tv_time)

    fun bind(oilPrice: OilPrice) {
        tvProvince.text = oilPrice.city
        tvNinetyTwo.text = oilPrice.ninetyTwo
        tvNinetyFive.text = oilPrice.ninetyFive
        tvNinetyEight.text = oilPrice.ninetyEight
        tvZero.text = oilPrice.zero
        tvTime.text = oilPrice.time
    }

    companion object {
        fun create(parent: ViewGroup): OilPriceViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_oil_pricce, parent, false)
            return OilPriceViewHolder(view)
        }
    }
}