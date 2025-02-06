package org.shoplist.project

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun ShowToast(message: String) {
    val context: Context = LocalContext.current
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}