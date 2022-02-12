package com.example.demo.domain.memo

import com.example.demo.domain.user.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class Memo(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<Memo>(Memos)

    var user by User referencedOn Memos.userId

    var title by Memos.title
    var body by Memos.body

    var createdAt by Memos.createdAt
    var updatedAt by Memos.updatedAt
    var deletedAt by Memos.deletedAt
}
