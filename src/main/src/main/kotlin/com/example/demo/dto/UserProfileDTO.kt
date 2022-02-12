package com.example.demo.dto

import com.example.demo.domain.userprofile.UserProfile
import java.time.LocalDateTime

data class UserProfileDTO(
    val tel: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?
) {
    companion object {

        fun toMapping(userProfile: UserProfile): UserProfileDTO {

            return UserProfileDTO(
                userProfile.tel,
                userProfile.createdAt,
                userProfile.updatedAt,
                userProfile.deletedAt
            )
        }
    }
}
