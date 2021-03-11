package de.szut.splitit

data class DetailsItem(
        val id: Long,
        val title: String,
        val value: String,
        val detailsEntries: List<DetailsEntry>
)
