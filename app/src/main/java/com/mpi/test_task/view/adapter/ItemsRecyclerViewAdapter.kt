package com.mpi.test_task.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.mpi.test_task.services.models.ConfirmedPosition

class ItemsRecyclerViewAdapter(private val items: List<ConfirmedPosition>, clickListener: ConfirmationClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val delegates = AdapterDelegatesManager<List<ConfirmedPosition>>()

    init {
        delegates.addDelegate(ItemsAdapterDelegate(clickListener))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return delegates.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        delegates.onBindViewHolder(items, position, holder)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}