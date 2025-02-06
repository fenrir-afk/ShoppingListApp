package org.shoplist.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.platform.LocalWindowInfo
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIColor
import platform.UIKit.UIDevice
import platform.UIKit.UIFont
import platform.UIKit.UILabel
import platform.UIKit.UIView
import platform.UIKit.UIViewAnimationOptionCurveEaseInOut

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalComposeUiApi::class, ExperimentalForeignApi::class)
@Composable
actual fun ShowToast(message: String) {
    val toastLabel = UILabel().apply {
        text = message
        textColor = UIColor.whiteColor
        backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.6)
        textAlignment = NSTextAlignmentCenter
        font = UIFont.systemFontOfSize(14.0)
        numberOfLines = 0
        layer.cornerRadius = 10.0
        clipsToBounds = true
    }
    val maxWidth = LocalWindowInfo.current.containerSize.width.toDouble()
    val maxHeight = LocalWindowInfo.current.containerSize.height.toDouble()
    toastLabel.setFrame(CGRectMake(maxWidth / 4, maxHeight / 2, maxWidth / 2, 50.0))
    LocalUIViewController.current.view.addSubview(toastLabel)

    UIView.animateWithDuration(
        1.5,
        delay = 2.0,
        options = UIViewAnimationOptionCurveEaseInOut,
        animations = { toastLabel.alpha = 0.0 },
        completion = { toastLabel.removeFromSuperview() }
    )
}