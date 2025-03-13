package com.boilerplate_spring_boot_kotlin.repository

import com.boilerplate_spring_boot_kotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsByEmailIgnoreCase(email: String?): Boolean

    fun findByEmail(email: String): User?

}
