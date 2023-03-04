package dev.rafaz.routes

import dev.rafaz.database.DAO
import dev.rafaz.database.TypeTransformation.toData
import dev.rafaz.dtos.AddAttributeDTO
import dev.rafaz.dtos.CategoryDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoryRouting() {
    route(path = "/category") {
        get {
            val categoriesEntity = DAO.Category.allCategories()
            call.respond(categoriesEntity.map { it.toData() })
        }
        get(path = "{id?}") {
            val id = call.parameters["id"]?.toInt() ?: return@get call.respondText(text = "Missing id", status = HttpStatusCode.BadRequest)
            val category = DAO.Category.getCategory(id) ?: return@get call.respondText(
                text = "No category with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(category)
        }
        post {
            val dto = call.receive<CategoryDTO>()
            val category = DAO.Category.newCategory(dto.parentId, dto.name)
            dto.attributesId?.forEach {
                DAO.Category.addAttribute(categoryId = category.id.value, it)
            }
            call.respondText(
                text = "Customer stored correctly",
                status = HttpStatusCode.Created
            )
        }
        post(path = "{id}/addcategory") {
            val id = call.parameters["id"]?.toInt() ?: return@post call.respondText(text = "Missing id", status = HttpStatusCode.BadRequest)
            val addAttributeDTO = call.receive<AddAttributeDTO>()
            DAO.Category.addAttribute(id, addAttributeDTO.attributeId)
            call.respondText(
                text = "Customer stored correctly",
                status = HttpStatusCode.Created
            )
        }
    }
}