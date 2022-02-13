package com.example.demo.app_service.user

import com.example.demo.infra.hawaii.tables.records.UserRecord
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.time.Instant
import java.time.ZoneOffset
import java.util.*

data class CreateTweetRequest(val message: String, val comment: String?)

class UserDto(
    val id: UUID,
    val username: String,
    val passwordHash: String,
    val nickname: String,
    val firstName: String?,
    val lastName: String?,
    val authorities: MutableList<SimpleGrantedAuthority>,
    val isEnabled: Boolean,
    val email: String?,
    val emailConfirmed: Boolean,
    val phoneNumber: String,
    val phoneNumberConfirmed: Boolean,
    val twoFactorEnabled: Boolean,
    val createdAt: Instant
)

fun UserRecord.toDto() = UserDto(
    id = id,
    username = username,
    passwordHash = passwordHash,
    nickname = nickname,
    firstName = firstName,
    lastName = lastName,
    authorities = authorities.split(",").map(::SimpleGrantedAuthority).toMutableList(),
    isEnabled = isEnabled,
    email = email,
    emailConfirmed = emailConfirmed,
    phoneNumber = phoneNumber,
    phoneNumberConfirmed = phoneNumberConfirmed,
    twoFactorEnabled = twoFactorEnabled,
    createdAt = createdAt.toInstant(ZoneOffset.UTC),
)


//fun getAuthorities(): MutableCollection<out GrantedAuthority> {
//    return authorities
//        .map(::SimpleGrantedAuthority)
//        .toMutableList()
//}
