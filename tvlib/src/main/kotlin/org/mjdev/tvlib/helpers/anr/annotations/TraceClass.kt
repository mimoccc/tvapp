package org.mjdev.tvlib.helpers.anr.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class TraceClass(
    val traceAllMethods:Boolean = true
)