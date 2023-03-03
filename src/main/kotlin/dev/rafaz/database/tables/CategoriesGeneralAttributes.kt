package dev.rafaz.database.tables

import org.jetbrains.exposed.sql.Table

object CategoriesGeneralAttributes : Table() {
    val category = reference("category", Categories)
    val attribute = reference("attribute", GeneralAttributes)

    override val primaryKey: PrimaryKey?
        get() = PrimaryKey(category, attribute)
}