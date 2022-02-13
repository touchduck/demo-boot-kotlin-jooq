package com.example.demo.app_service.auth

import com.example.demo.app_service.user.UserService
import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.records.UserRecord
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

@Transactional
@Service
class AuthServiceImpl(
    private val modelMapper: ModelMapper,
    private val userService: UserService,
    private val userRepository: UserRepository,
) : AuthService {

    private val log = LoggerFactory.getLogger(javaClass)

    override suspend fun signUp(signUpParam: SignUpParam): Mono<UserRecord> {

        val user = modelMapper.map(signUpParam, UserRecord::class.java)

        user.authorities = "ROLE_ADMIN"
        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(signUpParam.password)

        val createdUser = userRepository.save(user)

        return createdUser.toMono()
    }

    override suspend fun changePassword(userId: UUID, param: ChangePasswordParam): Mono<UserRecord> {

        val user = userService.getDetail(userId).awaitSingleOrNull() ?: return Mono.empty()

        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(param.password)

        userRepository.update(user)

        return user.toMono()
    }

    override suspend fun comparePassword(checkPassword: String, password_hash: String): Boolean {

        if (PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(checkPassword, password_hash)) {
            return true
        }

        return false

    }

    override suspend fun isRegisterUser(username: String): Mono<UserRecord> {

        userRepository.findUsername(username)?.let {
            return it.toMono()
        }

        return Mono.empty()
    }

    override suspend fun update(userId: UUID, param: UserSettingParam): Mono<UserRecord> {

        userRepository.findById(userId)?.let {

            it.firstName = param.firstname
            it.lastName = param.lastname

            userRepository.update(it)?.let { itSub ->
                return itSub.toMono()
            }
        }

        return Mono.empty()
    }

}
