package com.boilerplate_spring_boot_kotlin.repos

import com.boilerplate_spring_boot_kotlin.domain.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

}
