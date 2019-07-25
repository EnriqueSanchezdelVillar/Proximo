package com.example.primo.proximoconecta.Register

import java.io.Serializable

data class Register(
    val email: String,
    val password: String,
    val role: String,
    val firstName: String
) : Serializable