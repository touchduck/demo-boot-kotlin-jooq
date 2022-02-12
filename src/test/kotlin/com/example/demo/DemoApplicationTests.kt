package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@ActiveProfiles(profiles = ["default"])
@ContextConfiguration(classes = [DemoApplication::class])
//spring-boot annotations
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
//kotlin annotations
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)// can remove it default is RUNTIME
annotation class SpringWebFluxTest

@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {
    }

}
