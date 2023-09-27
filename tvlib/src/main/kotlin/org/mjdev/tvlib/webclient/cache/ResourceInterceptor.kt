package org.mjdev.tvlib.webclient.cache

interface ResourceInterceptor {
    fun interceptor(url: String?): Boolean
}