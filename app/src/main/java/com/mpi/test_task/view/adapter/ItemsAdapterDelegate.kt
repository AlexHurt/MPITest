package com.mpi.test_task.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.mpi.test_task.R
import com.mpi.test_task.services.models.ConfirmedPosition

class ItemsAdapterDelegate(private val clickListener: ConfirmationClickListener) : AdapterDelegate<List<ConfirmedPosition>>() {

    override fun isForViewType(items: List<ConfirmedPosition>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.view_confirmed_item, parent, false)
        return ConfirmedPositionViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(
        items: List<ConfirmedPosition>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ConfirmedPositionViewHolder).bindData(items[position])
    }

    class ConfirmedPositionViewHolder(itemView: View, private val clickListener: ConfirmationClickListener) : RecyclerView.ViewHolder(itemView) {

        var storageNumberTextView: TextView = itemView.findViewById(R.id.confirmed_item_storage_number_text_view)
        var serialNumberTextView: TextView = itemView.findViewById(R.id.confirmed_item_serial_number_text_view)
        var marketplaceTextView: TextView = itemView.findViewById(R.id.confirmed_item_marketplace_text_view)
        var quantityTextView: TextView = itemView.findViewById(R.id.confirmed_item_quantity_text_view)

        fun bindData(item: ConfirmedPosition) {
            storageNumberTextView.text = item.storageLevelName
            serialNumberTextView.text = item.serialNumber
            marketplaceTextView.text = item.marketPlaceName
            quantityTextView.text = itemView.context.getString(R.string.quantity_with_units, item.quantity, item.unit)
            itemView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }
}