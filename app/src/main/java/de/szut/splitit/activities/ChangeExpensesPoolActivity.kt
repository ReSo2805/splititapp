package de.szut.splitit.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.R
import de.szut.splitit.database.DatabaseHelper
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.services.ExpensesPoolService
import de.szut.splitit.database.services.UserService
import de.szut.splitit.database.ContextInfo
import de.szut.splitit.view.ExpensesPoolUsersRecyclerViewAdapter


class ChangeExpensesPoolActivity : AppCompatActivity(), ExpensesPoolUsersRecyclerViewAdapter.OnDeleteCallback {

    private lateinit var expensesPoolService: ExpensesPoolService
    private lateinit var userService: UserService

    private lateinit var expensesPool: ExpensesPool
    private lateinit var users: ArrayList<User>

    private lateinit var expensesPoolNameEditText: EditText

    private lateinit var expensesPoolNewUserNameEditText: EditText
    private lateinit var expensesPoolAddUserButton: ImageButton

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

        expensesPoolNewUserNameEditText = findViewById(R.id.expenses_pool_new_user_name_edit_text)
        expensesPoolAddUserButton = findViewById(R.id.expenses_pool_add_user_button)
        expensesPoolAddUserButton.setOnClickListener {
            val newUserName: String = expensesPoolNewUserNameEditText.text.toString()
            users.add(User(name = newUserName))
            expensesPoolUsersRecyclerViewAdapter.notifyDataSetChanged()
            expensesPoolNewUserNameEditText.text.clear()
        }

        expensesPoolUsersRecyclerView = findViewById(R.id.expenses_pool_users_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        expensesPoolUsersRecyclerView.layoutManager = layoutManager

        when(resolveRequestCode()) {
            REQUEST_CODE_CREATE -> {
                expensesPool = ExpensesPool()
                users = arrayListOf()
                setRecyclerViewAdapterFor(users)
            }
            REQUEST_CODE_CHANGE -> {
                val expensesPoolId: Long = resolveExpensePoolId()
                expensesPool = expensesPoolService.findById(expensesPoolId)
                expensesPoolNameEditText.setText(expensesPool.name)
                users = userService.findByExpensesPoolId(expensesPool.expensesPoolId)
                setRecyclerViewAdapterFor(users)
            }
            else -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    override fun onDelete(contextInfo: ContextInfo) {
        val user: User = users.removeAt(contextInfo.targetViewPosition)
        userService.delete(user)
        expensesPoolUsersRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_change_expenses_pool, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            setResult(RESULT_CANCELED)
            finish()
            true
        }
        R.id.option_submit -> {
            expensesPool.name = expensesPoolNameEditText.text.toString()
            publishChanges(expensesPool, users)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun publishChanges(expensesPool: ExpensesPool, users: List<User>) = when(resolveRequestCode()) {
        REQUEST_CODE_CREATE, REQUEST_CODE_CHANGE -> {
            val expensesPoolId: Long = expensesPoolService.save(expensesPool) ?: expensesPool.expensesPoolId
            users.forEach { user -> user.expensesPoolId = expensesPoolId }
            userService.save(users)
            setResult(RESULT_OK)
            finish()
        }
        else -> {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    private fun resolveRequestCode(): Int {
        return intent.getIntExtra(REQUEST_CODE_KEY, -1)
    }

    private fun resolveExpensePoolId(): Long {
       return intent.getLongExtra(EXTRA_EXPENSES_POOL_ID, DatabaseHelper.ENTITY_DEFAULT_ID)
    }

    private fun setRecyclerViewAdapterFor(users: List<User>) {
        expensesPoolUsersRecyclerViewAdapter = ExpensesPoolUsersRecyclerViewAdapter(this,
                this as ExpensesPoolUsersRecyclerViewAdapter.OnDeleteCallback,
                users
        )
        expensesPoolUsersRecyclerView.adapter = expensesPoolUsersRecyclerViewAdapter
    }

}