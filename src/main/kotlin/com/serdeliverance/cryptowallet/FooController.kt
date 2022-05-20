package com.serdeliverance.cryptowallet

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/foo")
class FooController {

    @GetMapping
    fun foo(): String {
        return "foo"
    }
}