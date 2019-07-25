package com.example.primo.proximoconecta.Activities

data class Activity(
    val _id: String? = null,
    val address: String,
    val createdAt: String?=null,
    val description: String,
    val lat: Double?=null,
    val long: Double?=null,
    val title: String,
    val date: String,
    val updatedAt: String?=null,
    val userId: String? = null
)
