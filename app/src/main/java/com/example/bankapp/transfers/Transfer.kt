package com.example.bankapp.transfers

data class Transfer (
    val sender: String,
    val receiver: String,
    val amount: Double
    )