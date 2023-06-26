///*
// * Copyright (c) Milan Jurkulák 2023.
// * Contact:
// * e: mimoccc@gmail.com
// * e: mj@mjdev.org
// * w: https://mjdev.org
// */
//
//package org.mjdev.tvapp.ui.components
//
//import android.content.Context
//import android.graphics.PixelFormat
//import android.view.Gravity
//import android.view.View
//import android.view.WindowManager
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.AbstractComposeView
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleOwner
//import androidx.lifecycle.LifecycleRegistry
//import androidx.lifecycle.ViewModelStore
//import androidx.lifecycle.ViewModelStoreOwner
//import androidx.lifecycle.setViewTreeLifecycleOwner
//import androidx.lifecycle.setViewTreeViewModelStoreOwner
//import androidx.savedstate.SavedStateRegistry
//import androidx.savedstate.SavedStateRegistryController
//import androidx.savedstate.SavedStateRegistryOwner
//import androidx.savedstate.setViewTreeSavedStateRegistryOwner
//
//class MyComposeView(
//    val composeView: View
//) : AbstractComposeView(
//    composeView.context
//), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
//
//    private val savedStateRegistryController by lazy {
//        SavedStateRegistryController.create(this)
//    }
//
//    override val lifecycle: Lifecycle by lazy {
//        LifecycleRegistry(this)
//    }
//
//    override val viewModelStore: ViewModelStore by lazy {
//        ViewModelStore()
//    }
//
//    override val savedStateRegistry: SavedStateRegistry
//        get() = savedStateRegistryController.savedStateRegistry
//
//
//    private var viewShowing: Boolean = false
//
//    private fun handleLifecycleEvent(event: Lifecycle.Event) =
//        (lifecycle as LifecycleRegistry).handleLifecycleEvent(event)
//
//
//    private val windowManager by lazy {
//        composeView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//    }
//
//    private var params: WindowManager.LayoutParams = WindowManager.LayoutParams()
//        .apply {
//            gravity = Gravity.TOP
//            type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
//            token = composeView.applicationWindowToken
//            width = WindowManager.LayoutParams.WRAP_CONTENT
//            height = WindowManager.LayoutParams.WRAP_CONTENT
//            format = PixelFormat.TRANSLUCENT
//            flags = flags or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        }
//
//    init {
//        composeView.setViewTreeLifecycleOwner(this)
//        composeView.setViewTreeViewModelStoreOwner(this)
//        composeView.setViewTreeSavedStateRegistryOwner(this)
//    }
//
//    @Composable
//    override fun Content() {
//        // Some Compose Drawing Function
//    }
//
//    fun show() {
//        if (viewShowing) dismiss()
//        windowManager.addView(this, params)
//        viewShowing = true
//    }
//
//    fun dismiss() {
//        if (!viewShowing) return
//        disposeComposition()
//        windowManager.removeViewImmediate(this)
//        viewShowing = false
//    }
//
//}