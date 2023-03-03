package dev.rafaz.database.tables

import org.jetbrains.exposed.sql.Table

object CategoriesGeneralAttributes : Table() {
    val category    = reference("id_category", Categories)
    val attribute   = reference("id_attribute", GeneralAttributes)

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(category, attribute)
}