package de.szut.splitit.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.services.ExpensesPoolService
import de.szut.splitit.database.services.UserService
import de.szut.splitit.database.views.ContextInfo
import de.szut.splitit.view.ExpensesPoolUsersRecyclerViewAdapter


class ChangeExpensesPoolActivity : AppCompatActivity(), ExpensesPoolUsersRecyclerViewAdapter.OnDeleteCallback {

    private lateinit var expensesPoolService: ExpensesPoolService
    private lateinit var userService: UserService

    private lateinit var expensesPoolNameEditText: EditText
    private lateinit var expensesPoolUsersRecyclerView: RecyclerView
    private lateinit var expensesPoolUsersRecyclerViewAdapter: ExpensesPoolUsersRecyclerViewAdapter

    companion object {
        const val EXTRA_EXPENSES_POOL_ID: String = "expensesPoolId"
        const val REQUEST_CODE_KEY: String = "requestCode"
        const val REQUEST_CODE_CREATE: Int = 0
        const val REQUEST_CODE_CHANGE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        expensesPoolService = ExpensesPoolService(this)
        userService = UserService(this)

        setContentView(R.layout.activity_change_expenses_pool)
        actionBar?.setDisplayHomeAsUpEnabled(true);

        expensesPoolNameEditText = findViewById(R.id.expenses_pool_name_edit_text)
        expensesPoolUsersRecyclerView = findViewById(R.id.expenses_pool_users_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        expensesPoolUsersRecyclerView.layoutManager = layoutManager

        initializeActivity()
    }

    private fun initializeActivity() = when(resolveRequestCode()) {
        REQUEST_CODE_CREATE -> {
            //TODO initialize activity respectively
            expensesPoolUsersRecyclerViewAdapter = ExpensesPoolUsersRecyclerViewAdapter(this,
                    this as ExpensesPoolUsersRecyclerViewAdapter.OnDeleteCallback,
                    listOf(User(1L, 1L, "Manfred"))
            )
            expensesPoolUsersRecyclerView.adapter = expensesPoolUsersRecyclerViewAdapter
        }
        REQUEST_CODE_CHANGE -> {
            //TODO initialize activity respectively
        }
        else -> {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onDelete(contextInfo: ContextInfo) {
        Toast.makeText(this, "onDelete called", Toast.LENGTH_SHORT).show()
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            setResult(RESULT_CANCELED)
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun resolveRequestCode(): Int {
       return intent.getIntExtra(REQUEST_CODE_KEY, -1)
    }



    private fun publishChanges(expensesPool: ExpensesPool, users: List<User>) = when(resolveRequestCode()) {
        REQUEST_CODE_CREATE, REQUEST_CODE_CHANGE -> {
            users.forEach { user ->
                user.expensesPoolId = expensesPool.expensesPoolId
            }
            expensesPoolService.save(expensesPool)
            userService.save(users)
            setResult(RESULT_OK)
            finish()
        }
        else -> {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

}