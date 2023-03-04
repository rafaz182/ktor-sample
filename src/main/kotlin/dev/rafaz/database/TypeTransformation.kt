package dev.rafaz.database

import dev.rafaz.database.tables.CategoryEntity
import dev.rafaz.database.tables.GeneralAttributeEntity
import dev.rafaz.database.tables.GeneralOptionEntity
import dev.rafaz.models.Category
import dev.rafaz.models.GeneralAttribute
import dev.rafaz.models.GeneralOption

object TypeTransformation {

    fun CategoryEntity.toData(): Category {
        return Category(
            id = id.value,
            parent = parent?.toData(),
            name = name,
            attributes = attribute.map { it.toData() }
        )
    }

    fun GeneralAttributeEntity.toData(): GeneralAttribute {
        return GeneralAttribute(
            id = id.value,
            name = name,
            description = description,
            options = options.map { it.toData() }
        )
    }

    fun GeneralOptionEntity.toData(): GeneralOption {
        return GeneralOption(
            id = this.id.value,
            value = this.value
        )
    }
}