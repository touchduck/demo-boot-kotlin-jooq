package com.example.demo.app_service.auth

import com.example.demo.app_service.auth.param.ChangePasswordParam
import com.example.demo.app_service.auth.param.SignUpParam
import com.example.demo.app_service.auth.param.UserSettingParam
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import reactor.core.publisher.Mono
import java.util.*

interface AuthService {

    suspend fun signUp(signUpParam: SignUpParam): Mono<UsersRecord>

    suspend fun changePassword(userId: UUID, param: ChangePasswordParam): Mono<UsersRecord>

    suspend fun comparePassword(checkPassword: String, password_hash: String): Boolean

    suspend fun isRegistedUser(username: String): Mono<UsersRecord>

    suspend fun update(userId: UUID, param: UserSettingParam): Mono<UsersRecord>
}
