package de.szut.splitit

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewUtil {

    companion object {
        fun withLayoutManager(activity: Activity, resId: Int): RecyclerView {
            val recyclerView: RecyclerView = activity.findViewById(resId)
            val layoutManager = LinearLayoutManager(activity)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            recyclerView.layoutManager = layoutManager
            return recyclerView
        }
    }

}