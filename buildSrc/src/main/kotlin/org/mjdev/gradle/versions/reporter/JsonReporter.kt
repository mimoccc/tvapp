/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.reporter

import org.mjdev.gradle.versions.result.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.gradle.api.Project
import java.io.OutputStream

class JsonReporter(
    override val project: Project,
    override val revision: String,
    override val gradleReleaseChannel: String,
) : AbstractReporter(project, revision, gradleReleaseChannel) {
    override fun write(printStream: OutputStream, result: Result) {
        val jsonAdapter = moshi
            .adapter(Result::class.java)
            .serializeNulls()
            .indent(" ")
        val json = jsonAdapter.toJson(result).trimMargin()
        printStream.println(json)
    }

    override fun getFileExtension(): String  = "json"

    companion object {
        private val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }
}
