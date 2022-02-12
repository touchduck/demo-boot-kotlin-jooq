package com.example.demo.config

// if using jooq, not need this.

//import com.example.demo.domain.memo.Memos
//import com.example.demo.domain.user.Users
//import com.example.demo.domain.userprofile.UserProfiles
//import org.jetbrains.exposed.spring.SpringTransactionManager
//import org.jetbrains.exposed.sql.Database
//import org.jetbrains.exposed.sql.SchemaUtils
//import org.jetbrains.exposed.sql.transactions.transaction
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.transaction.annotation.EnableTransactionManagement
//import org.springframework.transaction.annotation.TransactionManagementConfigurer
//import javax.sql.DataSource
//
//@Configuration
//@EnableTransactionManagement
//class DBConfiguration(@Autowired val dataSource: DataSource) : TransactionManagementConfigurer {
//
//    init {
//        Database.connect(dataSource)
//
//        transaction {
//
//            //addLogger(StdOutSqlLogger)
//
//            SchemaUtils.create(Users, UserProfiles, Memos)
//        }
//    }
//
//    @Bean
//    override fun annotationDrivenTransactionManager() = SpringTransactionManager(dataSource)
//}
