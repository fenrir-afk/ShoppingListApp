package org.shoplist.project

import androidx.compose.ui.window.ComposeUIViewController
import org.shoplist.project.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }