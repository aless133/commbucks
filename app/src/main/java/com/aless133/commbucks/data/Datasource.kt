package com.aless133.commbucks.data

import android.util.Log
import com.example.commbucks.model.Event

public fun getEvents(): List<Event> {
    Log.d("DATASOURCE", "getEvents");
    return listOf(
        Event("111111", "Тусовка", 5, 100),
        Event("'222222", "Поездка", 16, 100500),
    )
}