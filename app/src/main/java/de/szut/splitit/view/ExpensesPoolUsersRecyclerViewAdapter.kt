package de.szut.splitit.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.ContextInfo

class ExpensesPoolUsersRecyclerViewAdapter(
        private val context: Context,
        private val deleteCallback: OnDeleteCallback,
        private val expensesPoolUsers: List<User>)
    : RecyclerView.Adapter<ExpensesPoolUsersRecyclerViewAdapter.ViewHolder>() {

    interface OnDeleteCallback {
        fun onDelete(contextInfo: ContextInfo)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val expensesPoolUserNameTextView: TextView =
                view.findViewById(R.id.expenses_pool_user_name_text_view)
        val expensesPoolElementDeleteButton: ImageButton =
                view.findViewById(R.id.expenses_pool_element_delete_button)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_expenses_pool_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expensesPoolUser: User = expensesPoolUsers[position]

        holder.itemView.tag = expensesPoolUser.expensesPoolId
        holder.expensesPoolUserNameTextView.text = expensesPoolUser.name
        holder.expensesPoolElementDeleteButton.setOnClickListener {
            deleteCallback.onDelete(ContextInfo(holder.adapterPosition, holder.itemView.tag.toString().toLong()))
        }
    }

    override fun getItemCount(): Int {
        return expensesPoolUsers.size
    }


}