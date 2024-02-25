package com.aless133.commbucks.model

data class TransactionNew(
    var from: String,
    var to: List<String>,
    var sum: Number,
)

data class Transaction(
    var from: String,
    var to: String,
    var sum: Number,
)
