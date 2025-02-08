package org.shoplist.project.shopList.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            CircularProgressIndicator(
                color = Color.White
            )
        }
    }else{
        val state = rememberPagerState {screenstate.itemLists.size + 1}
        HorizontalPager(
            state = state,
            modifier = modifier.fillMaxSize().background(brown)
        ) { page ->
            if(screenstate.listsContent.isNotEmpty()){
                if(page == screenstate.itemLists.size ){
                    AddNewListScreen(screenstate = screenstate)
                }else{
                    if(screenstate.listsContent.isNotEmpty())
                        Box(
                            modifier = modifier.fillMaxSize()
                        ){
                            Column(
                                modifier = modifier.fillMaxSize().padding(40.dp),
                                horizontalAlignment = Alignment.CenterHorizontally

                            ){
                                Text(
                                    text = "Current key: ${screenstate.key}",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                )
                                LazyColumn {
                                    items(screenstate.listsContent[page]){item ->
                                        LazyCollItem("id: ${item.id} name: ${item.name} created: ${item.created}")
                                    }
                                }
                            }
                        }

                }
            }else{
                AddNewListScreen(screenstate = screenstate)
            }
        }
    }
}

@Composable
fun AddNewListScreen(modifier: Modifier = Modifier,screenstate: ShoppingScreenState) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Current key: ${screenstate.key}",
                fontSize = 20.sp,
                color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = "Create new shopping list"
            ){

            }
        }
    }
}