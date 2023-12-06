package org.mjdev.tvlib.ui.components.special///*
// *  Copyright (c) Milan Jurkulák 2023.
// *  Contact:
// *  e: mimoccc@gmail.com
// *  e: mj@mjdev.org
// *  w: https://mjdev.org
// */
//
//package org.mjdev.tvlib.ui
//
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.AnimationVector2D
//import androidx.compose.animation.core.Spring.StiffnessMediumLow
//import androidx.compose.animation.core.VectorConverter
//import androidx.compose.animation.core.spring
//import androidx.compose.foundation.layout.offset
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.composed
//import androidx.compose.ui.layout.onPlaced
//import androidx.compose.ui.layout.positionInParent
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.round
//import kotlinx.coroutines.launch
//
//@Suppress("unused")
//fun Modifier.animatePlacement(): Modifier = composed {
//    val scope = rememberCoroutineScope()
//    var targetOffset by remember { mutableStateOf(IntOffset.Zero) }
//    var animatable by remember {
//        mutableStateOf<Animatable<IntOffset, AnimationVector2D>?>(null)
//    }
//    onPlaced {
//        // Calculate the position in the parent layout
//        targetOffset = it.positionInParent().round()
//    }.offset {
//        // Animate to the new target offset when alignment changes.
//        val anim = animatable ?: Animatable(targetOffset, IntOffset.VectorConverter)
//            .also { animatable = it }
//        if (anim.targetValue != targetOffset) {
//            scope.launch {
//                anim.animateTo(targetOffset, spring(stiffness = StiffnessMediumLow))
//            }
//        }
//        // Offset the child in the opposite direction to the targetOffset, and slowly catch
//        // up to zero offset via an animation to achieve an overall animated movement.
//        animatable?.let { it.value - targetOffset } ?: IntOffset.Zero
//    }
//}
