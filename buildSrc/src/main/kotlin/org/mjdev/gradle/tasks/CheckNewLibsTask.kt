/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import okhttp3.OkHttpClient
import okhttp3.Request
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.internal.impldep.org.tomlj.Toml
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.writeToFile
import org.xml.sax.InputSource
import java.io.FileReader
import java.io.StringReader
import java.net.URI
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathFactory

open class CheckNewLibsTask : BaseTask() {

    @Optional
    @Input
    var tomlFilePath = "gradle/libs.versions.toml"

    @Optional
    @Input
    var reportFilePath = "dependencies.md"

    private val tomlFile
        get() = project.rootDir.resolve(tomlFilePath)

    private val reportFile
        get() = project.rootDir.resolve(reportFilePath)

    private val isTomlFileExists
        get() = tomlFile.exists()

    private val newLibs = mutableListOf<String>()
    private val missingLibs = mutableListOf<String>()

    private val repoURL = URI("https://repo1.maven.org")
    private val toml by lazy {
        FileReader(tomlFile).use { file ->
            Toml.parse(file)
        }
    }

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val dbf: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val xpathFactory: XPathFactory = XPathFactory.newInstance()
    private val expression = "//metadata//versioning//release"
    private val releasePath = xpathFactory.newXPath().compile(expression)

    init {
        group = "mjdev"
        description = "This task check new libraries versions in project."
        outputs.upToDateWhen { false }
    }

    private fun getLatestVersion(
        packageName: String?,
        mavenTarget: URI?,
        dbf: DocumentBuilderFactory,
        releasePath: XPathExpression
    ): String {
        val (groupId, artifactId) = packageName?.split(":") ?: emptyList()
        val path = "${groupId.replace(".", "/")}/$artifactId"
        val request = Request.Builder()
            .url("${mavenTarget}/maven2/$path/maven-metadata.xml")
            .build()
        val xmlString = okHttpClient.newCall(request).execute().let { response ->
            if (response.code != 200) {
                "Could not find value in Maven Central. Need to check manually."
            } else {
                response.body.string()
            }
        }
        val db = dbf.newDocumentBuilder()
        val document = db.parse(InputSource(StringReader(xmlString)))
        return releasePath.evaluate(document, XPathConstants.STRING).toString()
    }

    private fun printInfo() {
        StringBuilder().apply {
            appendLine("## Project libraries check")
            if (newLibs.size == 0 && missingLibs.size == 0) {
                appendLine("* No new libs detected for project.")
                appendLine("* All libs up to date.")
            } else {
                if (newLibs.size > 0) {
                    appendLine("* New libs detected for project:")
                    newLibs.forEach { line -> appendLine(" - $line") }
                }
                if (missingLibs.size > 0) {
                    appendLine("* Missing version.ref - Need to check manually")
                    missingLibs.forEach { line -> appendLine(" - $line") }
                }
            }
        }.toString().writeToFile(reportFile)
    }

    override fun onClean() {
        if (reportFile.exists()) {
            reportFile.delete()
        }
    }

    override fun onAssemble() {
        if (isTomlFileExists) {
            val versions = toml.getTable("versions")?.toMap()
            val libraries = toml.getTable("libraries")
            val (currentVersionRefs, missingVersions) = libraries?.keySet()?.associate {
                val module = libraries.getString("$it.module")
                val versionRef = libraries.getString("$it.version.ref")
                module to versionRef
            }?.entries?.partition { it.value != null }.let {
                Pair(emptyList<Map.Entry<String, String>>(), emptyList<Map.Entry<String, String>>())
            }
            val currentVersions = currentVersionRefs.associate {
                it.key to versions?.getValue(it.value)
            }
            val latestVersions = currentVersions.mapValues { (packageName, _) ->
                getLatestVersion(
                    packageName,
                    repoURL,
                    dbf,
                    releasePath
                )
            }
            currentVersions.toSortedMap().forEach { (packageName, currentVersion) ->
                val latestVersion = latestVersions.getValue(packageName)
                if (currentVersion != latestVersion) {
                    newLibs.add("$packageName: $currentVersion -> $latestVersion")
                }
            }
            if (missingVersions.isNotEmpty()) {
                missingLibs.add(missingVersions.map { it.key }.sortedBy { it }.joinToString("\n"))
            }
            printInfo()
        }
    }
}
