package com.example.demo.app_service.auth

import com.example.demo.infra.hawaii.tables.records.UserRecord
import java.util.*

interface AuthService {

    fun signUp(signUpParam: SignUpParam): UserRecord

    fun changePassword(userId: UUID, param: ChangePasswordParam): UserRecord?

    fun comparePassword(checkPassword: String, password_hash: String): Boolean

    fun isRegisterUser(username: String): UserRecord?

    fun update(userId: UUID, param: UserParam): UserRecord?
}
