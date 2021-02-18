package de.szut.splitit.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import de.szut.splitit.R

class ChangeExpensesPoolActivity : AppCompatActivity() {

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

    private fun initializeActivity() {
        when(intent.getIntExtra(REQUEST_CODE_KEY, -1)) {
            REQUEST_CODE_CREATE -> {
                //TODO handle create
                Toast.makeText(this, "Create", Toast.LENGTH_LONG).show()
            }
            REQUEST_CODE_CHANGE -> {
                //TODO handle change
            }
            else -> {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }
}