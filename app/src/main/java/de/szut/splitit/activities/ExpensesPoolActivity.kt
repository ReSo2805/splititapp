package de.szut.splitit.activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
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

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.expenses_pool_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.menuInfo == null) {
            return false;
        }

        val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val targetView: View = info.targetView
        val position: Int = info.position

        return when(item.itemId) {
            R.id.option_change_expenses_pool -> {
                Toast.makeText(this, "Option change", Toast.LENGTH_SHORT).show()
                false
            }
            R.id.option_delete_expenses_pool -> {
                Toast.makeText(this, "Option delete", Toast.LENGTH_SHORT).show()
                false
            }
            R.id.option_show_expenses_distribution_view -> {
                Toast.makeText(this, "Option distribution", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                Toast.makeText(this, "something else", Toast.LENGTH_SHORT).show()
                false
            }
        }

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
            ExpensesPoolDetailsRecyclerViewAdapter(this, this as View.OnCreateContextMenuListener, arrayListOf(details))

        expensesPoolDetailsRecyclerView.adapter = expensesPoolDetailsRecyclerViewAdapter
    }

}