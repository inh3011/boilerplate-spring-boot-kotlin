package com.boilerplate_spring_boot_kotlin.service

import com.boilerplate_spring_boot_kotlin.domain.User
import com.boilerplate_spring_boot_kotlin.model.UserDTO
import com.boilerplate_spring_boot_kotlin.repos.UserRepository
import com.boilerplate_spring_boot_kotlin.util.NotFoundException
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findAll(): List<UserDTO> {
        val users = userRepository.findAll(Sort.by("id"))
        return users.stream()
                .map { user -> mapToDTO(user, UserDTO()) }
                .toList()
    }

    fun `get`(id: Long): UserDTO = userRepository.findById(id)
            .map { user -> mapToDTO(user, UserDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(userDTO: UserDTO): Long {
        val user = User()
        mapToEntity(userDTO, user)
        return userRepository.save(user).id!!
    }

    fun update(id: Long, userDTO: UserDTO) {
        val user = userRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(userDTO, user)
        userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    private fun mapToDTO(user: User, userDTO: UserDTO): UserDTO {
        userDTO.id = user.id
        userDTO.email = user.email
        userDTO.username = user.username
        userDTO.password = user.password
        return userDTO
    }

    private fun mapToEntity(userDTO: UserDTO, user: User): User {
        user.email = userDTO.email
        user.username = userDTO.username
        user.password = userDTO.password
        return user
    }

    fun emailExists(email: String?): Boolean = userRepository.existsByEmailIgnoreCase(email)

}
