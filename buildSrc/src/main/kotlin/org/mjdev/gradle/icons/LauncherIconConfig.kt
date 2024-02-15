//package org.mjdev.gradle.icons
//
//import org.gradle.api.tasks.Input
//import org.mjdev.gradle.icons.filter.ColorRibbonFilter
//import org.mjdev.gradle.icons.filter.GrayscaleFilter
//import org.mjdev.gradle.icons.filter.LauncherIconFilter
//import org.mjdev.gradle.icons.filter.OverlayFilter
//import java.awt.Color
//import java.io.File
//import java.io.Serializable
//
//@Suppress("unused", "DefaultLocale", "MemberVisibilityCanBePrivate")
//class LauncherIconConfig(var name: String) : Serializable {
//    private val mFilters: ArrayList<LauncherIconFilter> = ArrayList()
//
//    var enabled = true
//
//    val filters: ArrayList<LauncherIconFilter>
//        get() = mFilters
//
//    private fun filters(vararg filters: LauncherIconFilter) {
//        mFilters.addAll(listOf(*filters))
//    }
//
//    @Input
//    fun enable(enabled: Boolean): LauncherIconConfig {
//        this.enabled = enabled
//        return this
//    }
//
//    @Input
//    fun setFilters(filters: Iterable<LauncherIconFilter?>?): LauncherIconConfig {
//        filters?.let { fts -> fts.forEach { f -> f?.let { mFilters.add(it) } } }
//        return this
//    }
//
//    @Input
//    fun setFilters(filter: LauncherIconFilter): LauncherIconConfig {
//        mFilters.add(filter)
//        return this
//    }
//
//    fun customColorRibbonFilter(
//            name: String?,
//            ribbonColor: String?,
//            labelColor: String?,
//            position: String,
//            textSizeRatio: Float
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color.decode(ribbonColor), Color.decode(labelColor), ColorRibbonFilter.Gravity.valueOf(position.uppercase()), textSizeRatio)
//    }
//
//    fun customColorRibbonFilter(
//            name: String?,
//            ribbonColor: String?,
//            labelColor: String?,
//            gravity: String
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color.decode(ribbonColor), Color.decode(labelColor), ColorRibbonFilter.Gravity.valueOf(gravity.uppercase()))
//    }
//
//    fun customColorRibbonFilter(
//            name: String?,
//            ribbonColor: String?,
//            labelColor: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color.decode(ribbonColor), Color.decode(labelColor))
//    }
//
//    fun customColorRibbonFilter(
//            name: String?,
//            ribbonColor: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color.decode(ribbonColor))
//    }
//
//    fun customColorRibbonFilter(
//            ribbonColor: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color.decode(ribbonColor))
//    }
//
//    fun grayRibbonFilter(
//            name: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0x60, 0x60, 0x60, 0x99))
//    }
//
//    fun grayRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0x60, 0x60, 0x60, 0x99))
//    }
//
//    fun greenRibbonFilter(name: String?): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0, 0x72, 0, 0x99))
//    }
//
//    fun greenRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0, 0x72, 0, 0x99))
//    }
//
//    fun orangeRibbonFilter(
//            name: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 0x76, 0, 0x99))
//    }
//
//    fun orangeRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 0x76, 0, 0x99))
//    }
//
//    fun yellowRibbonFilter(
//            name: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 251, 0, 0x99))
//    }
//
//    fun yellowRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 251, 0, 0x99))
//    }
//
//    fun redRibbonFilter(
//            name: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 0, 0, 0x99))
//    }
//
//    fun redRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0xff, 0, 0, 0x99))
//    }
//
//    fun blueRibbonFilter(
//            name: String?
//    ): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0, 0, 255, 0x99))
//    }
//
//    fun blueRibbonFilter(): ColorRibbonFilter {
//        return ColorRibbonFilter(name, Color(0, 0, 255, 0x99))
//    }
//
//    fun overlayFilter(
//            fgFile: File?
//    ): OverlayFilter {
//        return OverlayFilter(fgFile!!)
//    }
//
//    fun grayscaleFilter(): GrayscaleFilter {
//        return GrayscaleFilter()
//    }
//}