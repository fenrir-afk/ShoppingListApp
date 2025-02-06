package org.shoplist.project.shopList.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.shoplist.project.core.presentation.brown


data class A(var name1:String)
@Composable
fun CustomPager(modifier: Modifier = Modifier) {
    var mutableList = mutableListOf<A>(A("sdsd"),A("13231"))
    val state = rememberPagerState { mutableList.size }
    HorizontalPager(
        state = state,
        modifier = modifier.fillMaxSize().background(brown)
    ) { page ->
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = mutableList[page].name1, fontSize = 32.sp)
        }
    }
}