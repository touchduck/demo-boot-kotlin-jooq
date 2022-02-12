package com.example.demo.domain.userprofile

import com.example.demo.domain.user.Users
import com.example.demo.util.TimeUtil
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.datetime


object UserProfiles : UUIDTable("user_profiles") {

    val userId = reference("user_id", Users)


    val tel = varchar("tel", length = 512)

    var createdAt = datetime("created_at").clientDefault { TimeUtil.getDateTimeNow() }
    var updatedAt = datetime("updated_at").clientDefault { TimeUtil.getDateTimeNow() }
    val deletedAt = datetime("deleted_at").nullable()
}
