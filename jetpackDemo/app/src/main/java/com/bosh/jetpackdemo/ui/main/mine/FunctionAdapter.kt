package com.bosh.jetpackdemo.ui.main.mine

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bosh.jetpackdemo.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author lzq
 * @date  2022/5/19
 */
class FunctionAdapter(data: MutableList<FunctionItem>)
    : BaseQuickAdapter<FunctionItem, BaseViewHolder>(R.layout.item_mine_function ,data) {


    override fun convert(holder: BaseViewHolder, item: FunctionItem) {
        holder.setText(R.id.tv_name, item.name)
    }

}

data class FunctionItem(
    var name: String
)