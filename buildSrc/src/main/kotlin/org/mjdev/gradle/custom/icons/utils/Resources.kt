/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("DEPRECATION")

package org.mjdev.gradle.custom.icons.utils

import groovy.util.XmlSlurper
import groovy.util.slurpersupport.GPathResult
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.util.Collections
import javax.xml.parsers.ParserConfigurationException
import kotlin.collections.ArrayList

object Resources {

    fun resourceFilePattern(name: String): String {
        return if (name.startsWith("@")) {
            val pair = name.substring(1).split("/".toRegex(), 2).toTypedArray()
            val baseResType = pair[0]
            val fileName = pair[1]
            "$baseResType*/$fileName.*"
        } else name
    }

    @Throws(SAXException::class, ParserConfigurationException::class, IOException::class)
    fun getLauncherIcons(manifestFile: File?): List<String> {
        val manifestXml = XmlSlurper().parse(manifestFile)
        val applicationNode = manifestXml.getProperty("application") as GPathResult
        val icon = applicationNode.getProperty("@android:icon").toString()
        val roundIcon = applicationNode.getProperty("@android:roundIcon").toString()
        val icons: MutableList<String> = ArrayList(2)
        if (icon.isNotEmpty()) {
            icons.add(icon)
        }
        if (roundIcon.isNotEmpty()) {
            icons.add(roundIcon)
        }
        return Collections.unmodifiableList(icons)
    }

}