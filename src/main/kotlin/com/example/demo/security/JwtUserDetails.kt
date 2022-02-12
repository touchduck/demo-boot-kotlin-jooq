package com.example.demo.security


import com.example.demo.infra.hawaii.tables.records.UsersRecord
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*


data class JwtUserDetails(

    private val id: UUID,

    private val username: String,
    private val password: String,
    private val nickname: String,
    private val authorities: String = "",

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?

) : UserDetails {

    companion object {

        fun toMapping(user: UsersRecord): JwtUserDetails {
            return JwtUserDetails(
                id = user.id,

                username = user.username,
                password = user.passwordHash,
                nickname = user.nickname,
                authorities = user.authorities,

                createdAt = user.createdAt,
                updatedAt = user.updatedAt,
                deletedAt = user.deletedAt
            )
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities.split(",")
            .map(::SimpleGrantedAuthority)
            .toMutableList()
    }

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}
