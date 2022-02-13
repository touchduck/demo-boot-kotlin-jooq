package com.example.demo.app_service.user

import com.example.demo.domain.user.UserRepository
import com.example.demo.infra.hawaii.tables.records.UsersRecord
import com.example.demo.util.Pagination
import com.example.demo.util.PaginationParam
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
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val modelMapper: ModelMapper,
) : UserService {

    private val log = LoggerFactory.getLogger(javaClass)

    override suspend fun create(userId: UUID, userParam: UserParam): Mono<UsersRecord> {

        val user = modelMapper.map(userParam, UsersRecord::class.java)

        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userParam.password)

        val createdUser = userRepository.save(user)

        return createdUser.toMono()
    }

    override suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<UsersRecord>> {

        val userList = userRepository.findAll(paginationParam.size, paginationParam.offset)

        return userList.toMono()
    }

    override suspend fun getDetail(userId: UUID): Mono<UsersRecord> {

        userRepository.findById(userId)?.let {
            return it.toMono()
        }

        return Mono.empty()
    }

    override suspend fun update(userId: UUID, userParam: UserParam): Mono<UsersRecord> {

        userRepository.findById(userId)?.let {
            it.nickname = userParam.nickname
            userRepository.updateById(it)?.let { itSub ->
                return itSub.toMono()
            }
        }

        return Mono.empty()
    }

    override suspend fun delete(userId: UUID): Mono<Int> {

        userRepository.deleteById(userId).let {
            return it.toMono()
        }
    }

}
