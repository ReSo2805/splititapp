package de.szut.splitit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.views.ExpensesPoolDetails

class ExpensesPoolDetailsRecyclerViewAdapter(
    private val context: Context,
    private val contextMenuCallback: ContextMenuCallback,
    private val expensesPoolDetails: List<ExpensesPoolDetails>
) : RecyclerView.Adapter<ExpensesPoolDetailsRecyclerViewAdapter.ViewHolder>() {

    data class ContextMenuInfo(var targetViewPosition: Int, var id: Long)

    interface ContextMenuCallback {
        fun onContextMenuClick(view: View, contextMenuInfo: ContextMenuInfo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.expenses_pool_name_text_view)
        val userCountTextView: TextView = view.findViewById(R.id.expenses_pool_user_count_text_view)
        val expenseCountTextView: TextView =
            view.findViewById(R.id.expenses_pool_expense_count_text_view)
        val expenseTotalTextView: TextView =
            view.findViewById(R.id.expenses_pool_expense_total_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expenses_pool_details, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details: ExpensesPoolDetails = expensesPoolDetails[position]
        holder.nameTextView.text = details.name
        holder.userCountTextView.text =
            context.resources.getQuantityString(
                R.plurals.expenses_pool_user_count,
                details.userCount,
                details.userCount
            )
        holder.expenseCountTextView.text =
            context.resources.getQuantityString(
                R.plurals.expenses_pool_expense_count,
                details.expenseCount,
                details.expenseCount
            )
        holder.expenseTotalTextView.text =
            context.getString(R.string.expenses_pool_expense_total, details.expenseTotal)

        holder.itemView.tag = details.expensesPoolId

        holder.itemView.setOnLongClickListener {
            val info: ContextMenuInfo = ContextMenuInfo(holder.adapterPosition,
                    holder.itemView.tag.toString().toLong())
            contextMenuCallback.onContextMenuClick(it, info)
            true
        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.setOnLongClickListener(null)
    }

    override fun getItemCount(): Int {
        return expensesPoolDetails.size
    }

}