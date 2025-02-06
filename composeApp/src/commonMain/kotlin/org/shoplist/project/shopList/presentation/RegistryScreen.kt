package org.shoplist.project.shopList.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.shoplist.project.core.presentation.brown
import org.shoplist.project.core.presentation.white
import org.shoplist.project.shopList.presentation.component.EnterKeyDialog

@Composable
fun RegistryScreen(
    modifier: Modifier = Modifier,
    navigation: () -> Unit
) {
    val showDialog =  remember { mutableStateOf(false) }
    if(showDialog.value)
        EnterKeyDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {

        }
    Column(
        modifier = modifier.background(brown).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            border = BorderStroke(2.dp, Color.Black),
            shape = CircleShape,
            onClick = {
                navigation()
            }
        ) {
            Icon(Icons.Default.Add,"", tint = white)
            Text(
                color = white,
                text = "Create new key",
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.Transparent),
            border = BorderStroke(2.dp, Color.Black),
            shape = CircleShape,
            onClick = {
                showDialog.value = true
            }
        ) {
            Icon(Icons.Default.Add,"", tint = white)
            Text(
                color = white,
                text = "Use existing key",
                fontSize = 14.sp
            )
        }
    }
}
