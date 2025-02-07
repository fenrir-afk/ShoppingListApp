package org.shoplist.project.shopList.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.shoplist.project.core.presentation.brown
import org.shoplist.project.shopList.presentation.ShoppingScreenState



data class A(var name1:String)
@Composable
fun CustomPager(
    modifier: Modifier = Modifier,
    screenstate: ShoppingScreenState
) {
    if(screenstate.isLoading){
        Box(
            modifier = modifier.fillMaxSize().background(brown),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        val state = rememberPagerState {screenstate.itemLists.size + 1}
        HorizontalPager(
            state = state,
            modifier = modifier.fillMaxSize().background(brown)
        ) { page ->
            if(page == screenstate.itemLists.size ){
                Text("Hello",color = Color.White)
            }else{
                LazyColumn {
                    items(screenstate.listsContent[page]){item ->
                        LazyCollItem("id: ${item.id} name: ${item.name} created: ${item.created}")
                    }
                }
            }
        }
    }
}