package de.szut.splitit.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.database.views.ExpensesPoolDetails

class ExpensesPoolDetailsRecyclerViewAdapter(private val expensesPoolDetails: List<ExpensesPoolDetails>):
        RecyclerView.Adapter<ExpensesPoolDetailsRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View? = null
//        val view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_price_factor_row, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details: ExpensesPoolDetails = expensesPoolDetails[position]
        //TODO binding of values to view
    }

    override fun getItemCount(): Int {
          return expensesPoolDetails.size
    }

}