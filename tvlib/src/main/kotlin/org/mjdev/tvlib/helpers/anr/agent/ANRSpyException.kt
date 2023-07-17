package org.mjdev.tvlib.helpers.anr.agent

class ANRSpyException(title:String, stacktrace:Array<StackTraceElement>):Throwable(title) {

    init {
        stackTrace = stacktrace
    }

}