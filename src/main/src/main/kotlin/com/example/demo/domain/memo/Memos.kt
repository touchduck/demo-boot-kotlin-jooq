package com.example.demo.domain.memo

import com.example.demo.domain.user.Users
import com.example.demo.util.TimeUtil
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object Memos : UUIDTable("memos") {

    val userId = reference("user_id", Users)

    val title = varchar("title", length = 512)
    val body = text("body")

    var createdAt = datetime("created_at").clientDefault { TimeUtil.getDateTimeNow() }
    var updatedAt = datetime("updated_at").clientDefault { TimeUtil.getDateTimeNow() }
    val deletedAt = datetime("deleted_at").nullable()
}
