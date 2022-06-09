package com.bosh.jetpackdemo.ui.oil.history

import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.entity.OilHistory
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author lzq
 * @date  2022/6/8
 */
class OilHistoryAdapter(data: MutableList<OilHistory>) :
    BaseQuickAdapter<OilHistory, BaseViewHolder>(R.layout.item_oil_history, data) {
    override fun convert(holder: BaseViewHolder, item: OilHistory) {
        holder.setText(R.id.tv_time, item.time)
        val amount = String.format("%.2f", item.addOil * item.addPrice)
        holder.setText(R.id.tv_add_oil, "${item.addOil}L x ${item.addPrice}元 = ${amount}元")
        holder.setText(R.id.tv_mile, "里程：${item.mile}米")
        holder.setText(R.id.tv_oil_left, "剩余油量：${item.OilLeft}L")
    }
}