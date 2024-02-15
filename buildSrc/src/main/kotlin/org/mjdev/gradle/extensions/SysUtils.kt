package org.mjdev.gradle.extensions

import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@Suppress("unused")
class SysUtils {
    companion object {

        fun runCommandWithOutput(project: Project, vararg cmd: String): SysCommandReturn {
            val output = ByteArrayOutputStream()
            try {
                val cmdString = cmd.joinToString(" ")
                project.exec {
                    standardOutput = output
                    errorOutput = output
                    commandLine = listOf("sh", "-c", cmdString)
                }.apply {
                    return SysCommandReturn(exitValue, output)
                }
            } catch (t: Throwable) {
                return SysCommandReturn(-1, t, output)
            }
        }
    }

    /**
     * System command output
     * */
    class SysCommandReturn {
        constructor(exitValue: Int, output: ByteArrayOutputStream) {
            text = String(output.toByteArray())
            exitCode = exitValue
        }

        constructor(exitValue: Int, t: Throwable?, output: ByteArrayOutputStream) {
            text = String(output.toByteArray())
            exitCode = exitValue
            error = t
        }

        var text: String = ""
            private set
        var exitCode: Int = 0
            private set
        var error: Throwable? = null
            private set

        val errorText: String
            get() {
                val s = StringBuilder()
                if (text.isNotEmpty()) {
                    s.append(text)
                    s.append("\n")
                }
                s.append(String.format("command exitCode: %s\n", exitCode))
                if (error != null) {
                    s.append(message)
                    s.append("\n")
                    s.append(stackTrace)
                    s.append("\n")
                }
                return s.toString()
            }

        val message: String?
            get() {
                return error?.message
            }

        val stackTrace: String?
            get() {
                return error?.let {
                    val o = ByteArrayOutputStream()
                    it.printStackTrace(PrintStream(o))
                    return String(o.toByteArray())
                }
            }

        val isError: Boolean
            get() {
                return !isOK
            }

        val isOK: Boolean
            get() {
                return ((error == null) && (exitCode == 0))
            }
    }
}