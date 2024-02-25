package com.aless133.commbucks.data

import android.util.Log
import com.example.commbucks.model.Event

public fun getEvents(): List<Event> {
    Log.d("DATASOURCE", "getEvents");
    return listOf(
        Event(1, "Тусовка", "111111"),
        Event(2, "Поездка", "222222"),
    )
}