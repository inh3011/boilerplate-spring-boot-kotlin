package com.boilerplate_spring_boot_kotlin.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class UserDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @UserEmailUnique
    var email: String? = null

    @NotNull
    @Size(max = 255)
    var username: String? = null

    @NotNull
    @Size(max = 255)
    var password: String? = null

}
