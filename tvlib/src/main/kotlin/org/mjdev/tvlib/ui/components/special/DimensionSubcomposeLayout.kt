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
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.Measurable
//import androidx.compose.ui.layout.Placeable
//import androidx.compose.ui.layout.SubcomposeLayout
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.unit.Constraints
//import androidx.compose.ui.unit.Density
//import androidx.compose.ui.unit.DpSize
//
//@Composable
//internal fun DimensionSubComposeLayout(
//    modifier: Modifier = Modifier,
//    density: Density = LocalDensity.current,
//    mainContent: @Composable () -> Unit,
//    dependentContent: @Composable (DpSize) -> Unit
//) = SubcomposeLayout(
//    modifier = modifier
//) { constraints: Constraints ->
//    val mainPlaceable = subcompose(
//        SlotsEnum.Main,
//        mainContent
//    ).map { measurable ->
//        measurable.measure(constraints.copy(
//            minWidth = 0,
//            minHeight = 0
//        ))
//    }.first()
//    val dependentPlaceable: Placeable = subcompose(
//        SlotsEnum.Dependent
//    ) {
//        dependentContent(
//            DpSize(
//                density.run { mainPlaceable.width.toDp() },
//                density.run { mainPlaceable.height.toDp() }
//            )
//        )
//    }.map { measurable: Measurable ->
//        measurable.measure(constraints)
//    }.first()
//    layout(
//        mainPlaceable.width,
//        mainPlaceable.height
//    ) {
//        dependentPlaceable.placeRelative(0, 0)
//    }
//}
//
///**
// * Enum class for SubcomposeLayouts with main and dependent Composables
// */
//enum class SlotsEnum { Main, Dependent }