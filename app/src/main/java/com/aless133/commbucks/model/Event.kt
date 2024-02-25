package com.example.commbucks.model

data class EventNew(
    var name: String,
)

data class Event(
    val id: String,
    var name: String,
    var count: Int,
    var budget: Number,
);
