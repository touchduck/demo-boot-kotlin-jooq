package com.example.demo.domain.user

import com.example.demo.util.TimeUtil
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.date
import org.jetbrains.exposed.sql.`java-time`.datetime


object Users : UUIDTable("users") {

    val username = varchar("username", length = 256).uniqueIndex()
    val password_hash = varchar("password_hash", length = 512)

    val nickname = varchar("nickname", length = 128)

    val firstName = varchar("first_name", length = 128).nullable()
    val lastName = varchar("last_name", length = 128).nullable()

    val authorities = varchar("authorities", length = 512)

    val isEnabled = bool("is_enabled").default(true)

    val email = varchar("email", length = 256).nullable()
    val emailConfirmed = bool("email_confirmed").default(false)

    val phoneNumber = varchar("phone_number", length = 128).nullable()
    val phoneNumberConfirmed = bool("phone_number_confirmed").default(false)
    val twoFactorEnabled = bool("two_factor_enabled").default(false)

    val lockoutEnd = datetime("lockout_end").nullable()
    val lockoutEnabled = bool("lockout_enabled").default(false)
    val accessFailedCount = integer("access_failed_count").default(0)

    val birthday = date("birthday").nullable()
    val sex = short("sex").default(0)

    var createdAt = datetime("created_at").clientDefault { TimeUtil.getDateTimeNow() }
    var updatedAt = datetime("updated_at").clientDefault { TimeUtil.getDateTimeNow() }
    val deletedAt = datetime("deleted_at").nullable()
}
