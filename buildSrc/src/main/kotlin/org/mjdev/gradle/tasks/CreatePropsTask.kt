/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.api.tasks.Input
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.buildDirectory
import org.mjdev.gradle.extensions.extension
import org.mjdev.gradle.extensions.println
import org.mjdev.gradle.extensions.mapToString
import java.io.FileInputStream
import java.util.Properties
import org.mjdev.gradle.plugin.config.base.IConfig

open class CreatePropsTask : BaseTask() {

    @Input
    var propsFilePath = "props.prop"

    @Input
    lateinit var propsClass: Class<*>

    private val propsFile
        get() = project.buildDirectory.resolve(propsFilePath)

    private val isPropFileExists
        get() = propsFile.exists()

    init {
        group = "mjdev"
        description = "This task configure properties in project."
        outputs.upToDateWhen { false }
    }

    private fun printInfo() {
        println("---------------------------------------------------------------------")
        println("Configuration for project :${project.name} ")
        println("class : $propsClass")
        println("file : $propsFile")
        println("---------------------------------------------------------------------")
        val props = Properties()
        props.load(FileInputStream(propsFile))
        props.forEach { prop ->
            println("${prop.key} = ${prop.value}")
        }
        println("---------------------------------------------------------------------")
    }

    override fun onClean() {
        if (propsFile.exists()) {
            propsFile.delete()
        }
    }

    override fun onAssemble() {
        val config: IConfig = project.extension(propsClass) as IConfig
        config
            .toMap()
            .mapToString(
                separator = "=",
                quoteFirst = false,
                quoteSecond = false,
                prefix = "",
                suffix = ""
            )
            .also { props ->
                propsFile.apply {
                    parentFile.mkdirs()
                    writeText(props)
                }
            }
        printInfo()
    }
}
