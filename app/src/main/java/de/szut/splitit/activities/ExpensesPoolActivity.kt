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
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.ContextInfo
import de.szut.splitit.database.services.ExpensesPoolService
import de.szut.splitit.database.views.ExpensesPoolDetails
import de.szut.splitit.view.ExpensesPoolDetailsRecyclerViewAdapter


class ExpensesPoolActivity : AppCompatActivity(), ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuCallback {

    private lateinit var expensesPoolService: ExpensesPoolService

    private lateinit var expensesPoolDetailsRecyclerView: RecyclerView
    private var targetContextMenuInfo: ContextInfo? = null

    private lateinit var expensesPoolDetailsLiveData: LiveData<List<ExpensesPoolDetails>>
    private var expensePoolDetails: ArrayList<ExpensesPoolDetails> = arrayListOf()
    private lateinit var expensesPoolDetailsRecyclerViewAdapter: ExpensesPoolDetailsRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expenses_pool)

        expensesPoolService = ExpensesPoolService(this)

        expensesPoolDetailsRecyclerView = findViewById(R.id.expenses_pool_details_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        expensesPoolDetailsRecyclerView.layoutManager = layoutManager

        expensesPoolDetailsLiveData = expensesPoolService.findAllExpensesPoolDetails()
        expensesPoolDetailsLiveData.observe(this, { details: List<ExpensesPoolDetails> ->
            expensesPoolDetailsRecyclerViewAdapter.setExpensesPoolDetails(details)
        })

        expensesPoolDetailsRecyclerViewAdapter =
                ExpensesPoolDetailsRecyclerViewAdapter(this,
                        this as ExpensesPoolDetailsRecyclerViewAdapter.ContextMenuCallback)

        expensesPoolDetailsRecyclerView.adapter = expensesPoolDetailsRecyclerViewAdapter

        registerForContextMenu(expensesPoolDetailsRecyclerView)
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
                                    contextInfo: ContextInfo) {
        targetContextMenuInfo = contextInfo
        view.showContextMenu()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_expenses_pool, menu)
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
                            expensesPoolService.deleteById(targetContextMenuInfo!!.id)
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
            Toast.makeText(this, "Changes successful", Toast.LENGTH_LONG).show()
            expensesPoolDetailsRecyclerViewAdapter.notifyDataSetChanged()
        }
        ChangeExpensesPoolActivity.REQUEST_CODE_CHANGE -> {
            Toast.makeText(this, "Changes successful", Toast.LENGTH_LONG).show()
        }
        else -> {
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(this, "Changes failed", Toast.LENGTH_LONG).show()
        }
    }

}