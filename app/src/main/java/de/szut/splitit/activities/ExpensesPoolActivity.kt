package de.szut.splitit.activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.views.ExpensesPoolDetails
import de.szut.splitit.view.ExpensesPoolDetailsRecyclerViewAdapter


class ExpensesPoolActivity : AppCompatActivity(), ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuCallback {

    private lateinit var expensesPoolDetailsRecyclerView: RecyclerView
    private var targetContextMenuInfo: ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuInfo? = null
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

    override fun onContextMenuClick(view: View,
                                    contextMenuInfo: ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuInfo) {
        targetContextMenuInfo = contextMenuInfo
        view.showContextMenu()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.expenses_pool_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(targetContextMenuInfo == null) {
            return false;
        }

        return when(item.itemId) {
            R.id.option_change_expenses_pool -> {
                val intent = Intent(this, ChangeExpensesPoolActivity::class.java).apply {
                    putExtra(ChangeExpensesPoolActivity.REQUEST_CODE_KEY, ChangeExpensesPoolActivity.REQUEST_CODE_CHANGE)
                    putExtra(ChangeExpensesPoolActivity.EXTRA_EXPENSES_POOL_ID, targetContextMenuInfo!!.id)
                }
                startActivityForResult(intent,
                        ChangeExpensesPoolActivity.REQUEST_CODE_CHANGE)
                true
            }
            R.id.option_delete_expenses_pool -> {
                AlertDialog.Builder(this)
                        .setTitle(R.string.dialog_confirmation_delete_title)
                        .setMessage(R.string.dialog_confirmation_delete_message)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.yes) { dialog, whichButton ->
                            //TODO delete pool
                        }
                        .setNegativeButton(R.string.no, null)
                        .show()
                true
            }
            R.id.option_show_expenses_distribution_view -> {
                val intent = Intent(this, ExpensesDistributionActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                Toast.makeText(this, "something else", Toast.LENGTH_SHORT).show()
                false
            }
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
            ExpensesPoolDetails(1L, "Ausgabenpool 1", 5, 5, 1000f)

        expensesPoolDetailsRecyclerViewAdapter =
            ExpensesPoolDetailsRecyclerViewAdapter(this,
                    this as ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuCallback, arrayListOf(details))

        expensesPoolDetailsRecyclerView.adapter = expensesPoolDetailsRecyclerViewAdapter

        registerForContextMenu(expensesPoolDetailsRecyclerView)
    }

}