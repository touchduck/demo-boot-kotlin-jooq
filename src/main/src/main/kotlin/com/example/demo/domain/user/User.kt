package com.example.demo.domain.user

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*


class User(id: EntityID<UUID>) : UUIDEntity(id) {

    companion object : UUIDEntityClass<User>(Users)

    var username by Users.username
    var passwordHash by Users.password_hash

    var nickname by Users.nickname

    var firstName by Users.firstName
    var lastName by Users.lastName

    var authorities by Users.authorities

    var isEnabled by Users.isEnabled

    var emai by Users.email
    var emailConfirmed by Users.emailConfirmed

    var phoneNumber by Users.phoneNumber
    var phoneNumberConfirmed by Users.phoneNumberConfirmed
    var twoFactorEnabled by Users.twoFactorEnabled

    var lockoutEnd by Users.lockoutEnd
    var lockoutEnabled by Users.lockoutEnabled
    var accessFailedCount by Users.accessFailedCount

    var birthday by Users.birthday
    var sex by Users.sex

    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
    var deletedAt by Users.deletedAt

    fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.split(",")
            .map(::SimpleGrantedAuthority)
            .toMutableList()
    }
}
