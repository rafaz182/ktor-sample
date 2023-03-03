package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories : IntIdTable() {
    val parentId    = reference("parent_id", Categories).nullable() //integer("parent_id").nullable()
    val name        = varchar("name", 128)
}

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(Categories)

    val parent      by CategoryEntity optionalReferrersOn Categories.parentId
    val name        by Categories.name
    val attribute   by GeneralAttributeEntity via CategoriesGeneralAttributes
}