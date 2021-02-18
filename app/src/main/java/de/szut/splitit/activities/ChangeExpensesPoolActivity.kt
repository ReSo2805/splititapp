package de.szut.splitit.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import de.szut.splitit.R
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.services.ExpensesPoolService
import de.szut.splitit.database.services.UserService


class ChangeExpensesPoolActivity : AppCompatActivity() {

    private lateinit var expensesPoolService: ExpensesPoolService
    private lateinit var userService: UserService

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
        initializeActivity()
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

    private fun initializeActivity() = when(resolveRequestCode()) {
        REQUEST_CODE_CREATE -> {
            //TODO initialize activity respectively
        }
        REQUEST_CODE_CHANGE -> {
            //TODO initialize activity respectively
        }
        else -> {
            setResult(RESULT_CANCELED)
            finish()
        }
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