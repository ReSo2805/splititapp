package de.szut.splitit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.DetailsItem
import de.szut.splitit.OnItemClickListener
import de.szut.splitit.R

class DetailsItemsRecyclerViewAdapter(
        private val context: Context,
        private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<DetailsItemsRecyclerViewAdapter.ViewHolder>() {

    private var detailsItems: List<DetailsItem> = arrayListOf()

    class ViewHolder(view: View,
                     itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        val detailsItemTitleTextView: TextView =
                view.findViewById(R.id.details_item_title_text_view)
        val detailsItemTotalTextView: TextView =
                view.findViewById(R.id.details_item_total_text_view)
        val detailEntriesRecyclerView: RecyclerView =
                view.findViewById(R.id.detail_entries_recycler_view)

        init {
            view.setOnClickListener {
                itemClickListener.onClick(it, it.tag.toString().toLong())
            }
        }
    }

    fun changeDataSet(detailsItems: List<DetailsItem>) {
        this.detailsItems = detailsItems
        notifyDataSetChanged()
    }


    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.setOnLongClickListener(null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_details, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailsItem: DetailsItem = detailsItems[position]
        holder.itemView.tag = detailsItem.id

        holder.detailsItemTitleTextView.text = detailsItem.title
        holder.detailsItemTotalTextView.text = detailsItem.value

        DetailsEntriesRecyclerViewAdapter(context, detailsItem.detailsEntries)
                .also {
                    holder.detailEntriesRecyclerView.adapter = it
                }

    }

    override fun getItemCount(): Int {
        return detailsItems.size
    }

}