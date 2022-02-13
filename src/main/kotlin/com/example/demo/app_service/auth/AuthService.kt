package com.example.demo.app_service.auth

import com.example.demo.infra.hawaii.tables.records.UserRecord
import reactor.core.publisher.Mono
import java.util.*

interface AuthService {

    suspend fun signUp(signUpParam: SignUpParam): Mono<UserRecord>

    suspend fun changePassword(userId: UUID, param: ChangePasswordParam): Mono<UserRecord>

    suspend fun comparePassword(checkPassword: String, password_hash: String): Boolean

    suspend fun isRegisterUser(username: String): Mono<UserRecord>

    suspend fun update(userId: UUID, param: UserSettingParam): Mono<UserRecord>
}
