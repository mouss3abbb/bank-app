package com.example.bankapp

data class Transfer (
    val sender: String,
    val receiver: String,
    val amount: Double
    )