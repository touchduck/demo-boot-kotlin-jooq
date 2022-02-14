package com.example.demo.app_service.auth

import com.example.demo.app_service.user.UserService
import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.records.UserRecord
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service
class AuthServiceImpl(
    private val modelMapper: ModelMapper,
    private val userService: UserService,
    private val userRepository: UserRepository,
) : AuthService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun signUp(signUpParam: SignUpParam): UserRecord {

        val user = modelMapper.map(signUpParam, UserRecord::class.java)

        user.authorities = "ROLE_ADMIN"
        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(signUpParam.password)

        val createdUser = userRepository.save(user)

        return createdUser
    }

    override fun changePassword(userId: UUID, param: ChangePasswordParam): UserRecord? {

        userService.getDetail(userId)?.let {
            it.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(param.password)
            userRepository.update(it)
            return it
        }

        return null
    }

    override fun comparePassword(checkPassword: String, password_hash: String): Boolean {

        if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(checkPassword, password_hash)) {
            return true
        }

        return false
    }

    override fun isRegisterUser(username: String): UserRecord? {

        userRepository.findUsername(username)?.let {
            return it
        }

        return null
    }

    override fun update(userId: UUID, param: UserParam): UserRecord? {

        userRepository.findById(userId)?.let {

            it.firstName = param.firstname
            it.lastName = param.lastname

            userRepository.update(it)?.let { itSub ->
                return itSub
            }
        }

        return null
    }

}
