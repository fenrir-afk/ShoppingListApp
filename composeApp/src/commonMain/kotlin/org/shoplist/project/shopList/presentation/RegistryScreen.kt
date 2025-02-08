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
import androidx.navigation.NavController
import org.shoplist.project.core.navigation.Route
import org.shoplist.project.core.presentation.brown
import org.shoplist.project.core.presentation.white
import org.shoplist.project.shopList.presentation.component.CustomButton
import org.shoplist.project.shopList.presentation.component.EnterKeyDialog

@Composable
fun RegistryScreen(
    modifier: Modifier = Modifier,
    onCreateKey: () -> Unit,
    onCheckKey: (String) -> Unit
) {
    val showDialog =  remember { mutableStateOf(false) }
    if(showDialog.value)
        EnterKeyDialog(
            value = "",
            setShowDialog = {
            showDialog.value = it
        },
            setValue = {

            },
            onButtonClick = { key:String ->
                onCheckKey(key)
            }
        )
    Column(
        modifier = modifier.background(brown).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = "Create new key"
        ){
            onCreateKey()
        }
        Spacer(modifier = Modifier.height(25.dp))
        CustomButton(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = "Use existing key"
        ){
            showDialog.value = true
        }
    }
}
