package com.aless133.commbucks

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.aless133.commbucks.ui.theme.CommbucksTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aless133.commbucks.data.UserPreferencesRepository
import com.aless133.commbucks.ui.events.EventsScreen
import com.aless133.commbucks.ui.events.EventsViewModel
import com.aless133.commbucks.ui.events.EventsViewModelFactory
import com.aless133.commbucks.ui.event.EventScreen
import com.aless133.commbucks.ui.event.EventViewModel
import com.aless133.commbucks.ui.event.EventViewModelFactory
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel

val LocalNavController =
    staticCompositionLocalOf<NavController> { error("No NavController found!") }

enum class CommbucksScreen() {
    Events, Event,
}

private const val USER_PREFERENCES_NAME = "user_preferences"
private val Context.userDataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val eventsViewModel: EventsViewModel =
            EventsViewModelFactory(UserPreferencesRepository(userDataStore)).create(EventsViewModel::class.java)

        val eventViewModel: EventViewModel =
            EventViewModelFactory(UserPreferencesRepository(userDataStore)).create(EventViewModel::class.java)

        eventsViewModel.viewModelScope.launch {
            eventsViewModel.userPreferencesRepository.updateUserName("Петя")
        }

        setContent {
            CommbucksTheme {
                val navController = rememberNavController()
//                val eventsViewModel: EventsViewModel = viewModel()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController,
                            startDestination = CommbucksScreen.Events.name,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(400, 0, LinearOutSlowInEasing)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    animationSpec = tween(400, 0, LinearOutSlowInEasing)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(400, 0, LinearOutSlowInEasing)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    animationSpec = tween(400, 0, LinearOutSlowInEasing)
                                )
                            })
                        {
                            composable(route = CommbucksScreen.Events.name) {
                                EventsScreen(eventsViewModel)
                            }
                            composable(route = CommbucksScreen.Event.name) {
                                EventScreen(eventViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

