package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.fransbudikashira.bigio.ui.theme.poppinsFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabRow(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    tab1Text: String,
    tab2Text: String,
    modifier: Modifier,
    content: @Composable (page: Int) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                selected = pagerState.currentPage == 0,
                text = {
                    Text(
                        text = tab1Text,
                        fontFamily = poppinsFontFamily
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            )
            Tab(
                selected = pagerState.currentPage == 1,
                text = {
                    Text(
                        text = tab2Text,
                        fontFamily = poppinsFontFamily
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )
        }
        HorizontalPager(
            verticalAlignment = Alignment.Top,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page -> content(page) }
    }
}