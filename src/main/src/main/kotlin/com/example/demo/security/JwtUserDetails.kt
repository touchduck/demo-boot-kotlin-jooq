package com.example.demo.security


import com.example.demo.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*


data class JwtUserDetails(

    private val id: UUID,

    private val username: String,
    private val password: String,
    private val authorities: String = "",
    private val nickname: String,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?

) : UserDetails {

    companion object {

        fun toMapping(user: User): JwtUserDetails {
            return JwtUserDetails(
                id = user.id.value,
                username = user.username,
                password = user.passwordHash,
                authorities = user.authorities,
                nickname = user.nickname,
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
