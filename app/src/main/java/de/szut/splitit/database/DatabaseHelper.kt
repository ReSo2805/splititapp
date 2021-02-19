package de.szut.splitit.database

import de.szut.splitit.database.entities.BaseEntity

class DatabaseHelper {

    companion object {
        const val ENTITY_DEFAULT_ID: Long = 0L
        val ENTITY_EXISTS: (BaseEntity) -> Boolean = { it -> it.getId() == ENTITY_DEFAULT_ID}
    }

}