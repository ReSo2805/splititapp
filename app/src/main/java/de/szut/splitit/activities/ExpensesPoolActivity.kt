package de.szut.splitit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.views.ExpensesPoolDetails
import de.szut.splitit.view.ExpensesPoolDetailsRecyclerViewAdapter

class ExpensesPoolActivity : AppCompatActivity() {

    private lateinit var expensesPoolDetailsRecyclerView: RecyclerView
    private lateinit var expensesPoolDetailsRecyclerViewAdapter: ExpensesPoolDetailsRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses_pool)
        initializeViews()
    }

    private fun initializeViews() {
        expensesPoolDetailsRecyclerView = findViewById(R.id.expenses_pool_details_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        expensesPoolDetailsRecyclerView.layoutManager = layoutManager

        val details =
            ExpensesPoolDetails("Ausgabenpool 1", 5, 5, 1000f)

        expensesPoolDetailsRecyclerViewAdapter =
            ExpensesPoolDetailsRecyclerViewAdapter(this, arrayListOf(details))

        expensesPoolDetailsRecyclerView.adapter = expensesPoolDetailsRecyclerViewAdapter
    }

}