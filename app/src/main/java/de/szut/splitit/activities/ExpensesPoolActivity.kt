package de.szut.splitit.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.option_create -> {
            val intent = Intent(this, ChangeExpensesPoolActivity::class.java).apply {
                putExtra(ChangeExpensesPoolActivity.REQUEST_CODE_KEY, ChangeExpensesPoolActivity.REQUEST_CODE_CREATE)
            }
            startActivityForResult(intent,
                ChangeExpensesPoolActivity.REQUEST_CODE_CREATE)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = when(requestCode.takeIf{ resultCode == RESULT_OK }) {
        ChangeExpensesPoolActivity.REQUEST_CODE_CREATE -> {
            //TODO get data from intent and call service classes
        }

        ChangeExpensesPoolActivity.REQUEST_CODE_CHANGE -> {
            //TODO get data from intent and call service classes
        }

        else -> {
            super.onActivityResult(requestCode, resultCode, data)
        }
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