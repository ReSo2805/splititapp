package de.szut.splitit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.DetailsEntry
import de.szut.splitit.R

class DetailsEntriesRecyclerViewAdapter(
        private val context: Context,
        private var detailsEntries: List<DetailsEntry>
): RecyclerView.Adapter<DetailsEntriesRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailsEntryTitleTextView: TextView =
                view.findViewById(R.id.details_entry_title_text_view)
        val detailsEntryValueTextView: TextView =
                view.findViewById(R.id.details_entry_value_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_details_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val detailsEntry: DetailsEntry = detailsEntries[position]

        holder.detailsEntryTitleTextView.text = detailsEntry.title
        holder.detailsEntryValueTextView.text = detailsEntry.value
    }

    override fun getItemCount(): Int {
        return detailsEntries.size
    }

}