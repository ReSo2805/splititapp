package de.szut.splitit.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.szut.splitit.R
import de.szut.splitit.database.entities.ExpensesPool
import de.szut.splitit.database.entities.User
import de.szut.splitit.database.services.ExpensesPoolService
import de.szut.splitit.database.services.UserService

class ChangeExpensesPoolActivity : AppCompatActivity() {

    private val userService: UserService = UserService(this)
    private val expensesPoolService: ExpensesPoolService = ExpensesPoolService(this)

    companion object {
        const val EXTRA_EXPENSES_POOL_ID: String = "expensesPoolId"
        const val REQUEST_CODE_KEY: String = "requestCode"
        const val REQUEST_CODE_CREATE: Int = 0
        const val REQUEST_CODE_CHANGE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_expenses_pool)
        initializeActivity()
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