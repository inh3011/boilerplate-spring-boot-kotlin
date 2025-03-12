package com.boilerplate_spring_boot_kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HomeResource {

    @GetMapping("/")
    fun index(): String = "\"Hello World!\""

}
