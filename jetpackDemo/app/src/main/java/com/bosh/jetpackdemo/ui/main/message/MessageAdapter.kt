package com.bosh.jetpackdemo.ui.main.message

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bosh.jetpackdemo.entity.MessageInfo

/**
 * @author lzq
 * @date  2022/2/22
 */
class MessageAdapter : PagingDataAdapter<MessageInfo, MessageViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder.create(parent)
    }

    companion object {
        val diffCallBack: DiffUtil.ItemCallback<MessageInfo> = object:
            DiffUtil.ItemCallback<MessageInfo>() {
            override fun areItemsTheSame(oldItem: MessageInfo, newItem: MessageInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MessageInfo, newItem: MessageInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}