package com.example.primo.proximoconecta.Login
import java.io.Serializable


data class Login(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val role: String)//​
// lo que envio
data class UserLogin(
    val email: String,
    val password: String
): Serializable












