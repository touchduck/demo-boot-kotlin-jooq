package com.example.demo.config

import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.jooq.impl.DefaultExecuteListenerProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration
import org.springframework.context.ApplicationContextException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
@ImportAutoConfiguration(JooqAutoConfiguration::class)
class JooqPersistentContextConfig(
    private val dataSource: DataSource,
    @Value(value = "\${spring.jooq.sql-dialect}")
    private val dialect: String
) {

    @Bean
    fun connectionProvider() = DataSourceConnectionProvider(TransactionAwareDataSourceProxy(dataSource))

    @Bean
    fun dsl() = DefaultDSLContext(configuration())


    fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()

        val settings = jooqConfiguration.settings()
        // https://www.jooq.org/doc/3.7/manual/sql-execution/crud-with-updatablerecords/optimistic-locking/
        settings.withExecuteWithOptimisticLocking(true)
            .withExecuteLogging(true)

        jooqConfiguration.set(settings)
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(DefaultExecuteListenerProvider(jooqToSpringExceptionTransformer()))

        if (dialect.isEmpty()) {
            throw RuntimeException("App Config Error! spring.jooq.sql-dialect MUST NOT BE EMPTY!")
        }
        val sqlDialect: SQLDialect = when (dialect.trim().uppercase(Locale.getDefault())) {
            "POSTGRES" -> SQLDialect.POSTGRES
            else -> {
                try {
                    SQLDialect.valueOf(dialect)
                } catch (e: Exception) {
                    throw ApplicationContextException(
                        "App Config Error! No Handler defined for spring.jooq.sql-dialect = '$dialect' !"
                                + " reason: ${e.message}"
                    )
                }
            }
        }
        jooqConfiguration.setSQLDialect(sqlDialect)

        return jooqConfiguration
    }

    @Bean
    fun jooqToSpringExceptionTransformer() = JOOQToSpringExceptionTransformer()

}
