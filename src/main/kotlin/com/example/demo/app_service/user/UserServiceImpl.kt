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

    override suspend fun create(userId: UUID, userParam: UserParam): Mono<UserRecord> {

        val user = modelMapper.map(userParam, UserRecord::class.java)

        user.passwordHash = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(userParam.password)

        val createdUser = userRepository.save(user)

        return createdUser.toMono()
    }

    override suspend fun getList(userId: UUID, paginationParam: PaginationParam): Mono<Pagination<UserRecord>> {

        val userList = userRepository.findAll(paginationParam.size, paginationParam.offset)

        return userList.toMono()
    }

    override suspend fun getDetail(userId: UUID): Mono<UserRecord> {

        userRepository.findById(userId)?.let {
            return it.toMono()
        }

        return Mono.empty()
    }

    override suspend fun update(userId: UUID, userParam: UserParam): Mono<UserRecord> {

        userRepository.findById(userId)?.let {
            it.nickname = userParam.nickname
            userRepository.update(it)?.let { itSub ->
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
