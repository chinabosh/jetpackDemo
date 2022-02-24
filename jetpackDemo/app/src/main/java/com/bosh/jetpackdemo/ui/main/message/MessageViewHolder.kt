package com.bosh.jetpackdemo.ui.main.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bosh.jetpackdemo.R
import com.bosh.jetpackdemo.entity.MessageInfo
import com.bosh.jetpackdemo.utils.DateUtils

/**
 * @author lzq
 * @date  2022/2/22
 */
class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val tvTitle : TextView = view.findViewById(R.id.tv_title)
    private val tvSubTitle: TextView = view.findViewById(R.id.tv_sub_title)
    private val tvTime: TextView = view.findViewById(R.id.tv_time)

    fun bind(messageInfo: MessageInfo?) {
        tvTitle.text = messageInfo?.title ?: "loading"
        tvSubTitle.text = messageInfo?.subTitle ?: "loading"
        tvTime.text = DateUtils.getShowTime(messageInfo?.createTime ?: "")
    }

    companion object {
        fun create(parent: ViewGroup): MessageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false)
            return MessageViewHolder(view)
        }
    }
}