package com.aless133.commbucks.ui.event

enum class EventTabs {
    Members,
    Operations,
}

data class EventUIState(
    var activeTab : EventTabs
)
