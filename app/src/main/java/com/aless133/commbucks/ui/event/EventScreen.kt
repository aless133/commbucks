package com.aless133.commbucks.ui.event

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aless133.commbucks.LocalNavController
import com.aless133.commbucks.R
import com.aless133.commbucks.model.UserPreferences
import com.aless133.commbucks.ui.components.FloatingAddButton
import com.aless133.commbucks.ui.components.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventScreen(
    eventViewModel: EventViewModel = viewModel()
) {
    val uiState by eventViewModel.uiState.collectAsState()
    val navController = LocalNavController.current
    val pages = listOf(
        stringResource(R.string.event_page_members), stringResource(R.string.event_page_operations)
    )
    val eventPageMembers = pages.indexOf(stringResource(R.string.event_page_members));
    val eventPageOperations = pages.indexOf(stringResource(R.string.event_page_members));
    val user by eventViewModel.userPreferencesRepository.userPreferencesFlow.collectAsState(initial = UserPreferences())

    Scaffold(
        topBar = { TopBar(title = "Второй экран") },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            val pagerState = rememberPagerState(initialPage = uiState.activePage) { pages.size }
            val coroutineScope = rememberCoroutineScope()
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                contentColor = MaterialTheme.colorScheme.onBackground,
//                modifier = Modifier.fillMaxWidth()
                divider = {}
            ) {
                pages.forEachIndexed { index, title ->
                    Tab(selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch { pagerState.animateScrollToPage(index) }
                            eventViewModel.activatePage(index)
                        }) {
                        Text(
                            text = title + user.name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_sm))
                        )
                    }

                }
            }
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    pages.indexOf(stringResource(R.string.event_page_members)) -> EventMembersPage()
                    pages.indexOf(stringResource(R.string.event_page_operations)) -> EventOperationsPage()
                    else -> null
                }
            }
        }
    }
}
