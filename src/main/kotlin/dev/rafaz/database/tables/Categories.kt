package dev.rafaz.database.tables

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories : IntIdTable() {
    val parentId    = reference("id_parent", Categories).nullable() //integer("parent_id").nullable()
    val name        = varchar("name", 128)
}

class CategoryEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CategoryEntity>(Categories)

    var parent      by CategoryEntity optionalReferencedOn Categories.parentId
    var name        by Categories.name
    val attribute   by GeneralAttributeEntity via CategoriesGeneralAttributes
}