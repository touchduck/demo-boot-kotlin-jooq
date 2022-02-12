package com.example.demo.domain.userprofile

import com.example.demo.domain.user.User
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class UserProfile(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<UserProfile>(UserProfiles)

    var user by User referencedOn UserProfiles.userId

    var tel by UserProfiles.tel

    var createdAt by UserProfiles.createdAt
    var updatedAt by UserProfiles.updatedAt
    var deletedAt by UserProfiles.deletedAt
}
