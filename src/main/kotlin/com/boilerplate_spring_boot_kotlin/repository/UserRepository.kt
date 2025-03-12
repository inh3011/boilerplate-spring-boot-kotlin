package com.boilerplate_spring_boot_kotlin.repository

import com.boilerplate_spring_boot_kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

}
