package com.example.demo.app_service.user

import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.records.UserRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Transactional
@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper,
) : UserService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun create(userId: UUID, userParam: UserParam): UserRecord? {

        val user = modelMapper.map(userParam, UserRecord::class.java)

        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userParam.password)

        val createdUser = userRepository.save(user)

        return createdUser
    }

    override fun getList(userId: UUID, paginationParam: PaginationParam): Pagination<UserRecord> {

        val userList = userRepository.findAll(paginationParam.size, paginationParam.offset)

        return userList
    }

    override fun getDetail(userId: UUID): UserRecord? {

        userRepository.findById(userId)?.let {
            return it
        }

        return null
    }

    override fun update(userId: UUID, userParam: UserParam): UserRecord? {

        userRepository.findById(userId)?.let {
            it.nickname = userParam.nickname
            userRepository.update(it)?.let { itSub ->
                return itSub
            }
        }

        return null
    }

    override fun delete(userId: UUID): Int {

        userRepository.deleteById(userId).let {
            return it
        }
    }

}
