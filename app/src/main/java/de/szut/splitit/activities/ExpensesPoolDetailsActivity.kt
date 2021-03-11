package de.szut.splitit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import de.szut.splitit.*
import de.szut.splitit.view.DetailsItemsRecyclerViewAdapter

class ExpensesPoolDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var detailsItemRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detailsEntries: List<DetailsEntry> = arrayListOf()

        val detailsItem: DetailsItem =
                DetailsItem(1, "Ausgabe 1", "300,00€", detailsEntries)

        detailsItemRecyclerView =
                RecyclerViewUtil.withLayoutManager(this, R.id.details_recycler_view)

        val detailsItemsRecyclerViewAdapter: DetailsItemsRecyclerViewAdapter =
                DetailsItemsRecyclerViewAdapter(this, this as OnItemClickListener)

        detailsItemRecyclerView.adapter = detailsItemsRecyclerViewAdapter
        detailsItemsRecyclerViewAdapter.changeDataSet(arrayListOf(detailsItem))

    }

    override fun onClick(v: View, id: Long) {
        TODO("Not yet implemented")
    }
}