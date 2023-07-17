@file:Suppress("PrivatePropertyName", "LocalVariableName")

package org.mjdev.tvlib.helpers.anr.agent

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue.IdleHandler
import org.mjdev.tvlib.helpers.anr.annotations.TraceClass
import org.mjdev.tvlib.helpers.anr.annotations.TraceMethod
import org.mjdev.tvlib.helpers.anr.models.MethodModel
import timber.log.Timber

class ANRSpyAgent constructor(
    builder: Builder
) : Thread() {

    private var mContext: Context
    private var mListener: ANRSpyListener? = null
    private var mShouldThrowException: Boolean = false
    private var TIME_OUT = 5000L
    private var mReportAnnotatedMethods: Boolean = false
    private var mListAnnotatedMedhods = mutableListOf<String>()
    private var mReportMethods = mutableListOf<MethodModel>()
    private val INTERVAL = 500L
    private val mHandler = Handler(Looper.getMainLooper())
    private var _timeWaited = 0L
    private val _mTesterWorker = Runnable {
        _timeWaited = 0L
    }
    private val mIdleHandler = IdleHandler {
        mListener?.onReportAvailable(mReportMethods)
        mReportMethods = mutableListOf()
        true
    }

    init {
        this.mListener = builder.getSpyListener()
        this.mShouldThrowException = builder.getThrowException()
        this.TIME_OUT = builder.getTimeOout()
        this.mReportAnnotatedMethods = builder.getReportAnnotatedMethods()
        this.mContext = builder.mContext
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Looper.getMainLooper().queue.addIdleHandler(mIdleHandler)
        } else {
            Timber.e("Can not load queue handler.")
            // todo iddle handler
        }
    }

    class Builder(var mContext: Context) {

        private var mListener: ANRSpyListener? = null
        private var mShouldThrowException: Boolean = false
        private var TIME_OUT = 5000L
        private var mEnableInstantReport: Boolean = false

        fun setSpyListener(listener: ANRSpyListener) = apply { this.mListener = listener }

        fun getSpyListener() = this.mListener

        fun setThrowException(throwexception: Boolean) = apply {
            this.mShouldThrowException = throwexception
        }

        fun getThrowException() = this.mShouldThrowException

        fun setTimeOut(timeout: Long) = apply { TIME_OUT = timeout }

        fun getTimeOout() = TIME_OUT

        fun enableReportAnnotatedMethods(enable: Boolean) = apply { mEnableInstantReport = enable }

        fun getReportAnnotatedMethods() = this.mEnableInstantReport

        fun build(): ANRSpyAgent = ANRSpyAgent(this)

    }

    @Suppress("UNUSED_VARIABLE")
    override fun run() {
        while (!isInterrupted) {
            val stacktrace = Looper.getMainLooper().thread.stackTrace
            isResumeExists(stacktrace)
            _timeWaited += INTERVAL
            mListener?.onWait(_timeWaited)
            mHandler.post(_mTesterWorker)
            sleep(INTERVAL)
            if (_timeWaited > TIME_OUT) {
                val listPackageMethods = findPackagMethods(stacktrace)
                mListener?.onAnrDetected(
                    "$THREAD_TITLE Main thread blocked for: $_timeWaited ms",
                    stackTrace,
                    findPackagMethods(stacktrace)
                )
                if (mShouldThrowException) {
                    throwException(Looper.getMainLooper().thread.stackTrace)
                }
            }
            if (mReportAnnotatedMethods) {
                instantReport()
            }
        }
    }

    private fun isResumeExists(stacktrace: Array<StackTraceElement>) {
        val allstacks = getAllStackTraces()
        Timber.w("Threads------------")
        for (entity in allstacks) {
            Timber.e("${entity.key}")
        }
        Timber.w("Threads------------\nn")
        Timber.w("Threads------------\n")
        for (item in stacktrace) {
            Timber.e(item.toString())
        }
        Timber.w("Threads------------" + "\n")
    }

    private fun throwException(stackTrace: Array<StackTraceElement>) {
        throw ANRSpyException(THREAD_TITLE, stackTrace)
    }

    private fun instantReport() {
        val stacktrace = Looper.getMainLooper().thread.stackTrace
        for (element in stacktrace) {
            addAnnotatedMethods(element.className)
            val methodexists = mListAnnotatedMedhods.find {
                element.methodName.lowercase().startsWith(it.lowercase())
            }
            if (methodexists != null) {
                addMethod(methodexists, Looper.getMainLooper().thread)
                return
            }
        }
    }

    @Synchronized
    fun addMethod(methodName: String, thread: Thread) {
        val exists = mReportMethods.find {
            (it.name.lowercase() == methodName.lowercase())
        }
        if (exists != null) {
            for (item in mReportMethods) {
                if (item.name.lowercase() == exists.name.lowercase()) {
                    item.elapsedTime += INTERVAL
                    return
                }
            }
        } else {
            mReportMethods.add(
                MethodModel(
                    System.currentTimeMillis(), methodName, thread, 0
                )
            )
        }
    }

    private fun addAnnotatedMethods(className: String) {
        try {
            val clazz = Class.forName(className)
            val annotation = clazz.getAnnotation(TraceClass::class.java)
            annotation?.let { tc ->
                if (tc.traceAllMethods) {
                    for (m in clazz.declaredMethods) {
                        val exists = mListAnnotatedMedhods.find { s ->
                            s.lowercase() == m.name.lowercase()
                        }
                        if (exists == null) {
                            mListAnnotatedMedhods.add(m.name)
                        }
                    }
                } else {
                    for (m in clazz.declaredMethods) {
                        m.getAnnotation(TraceMethod::class.java).let { ann ->
                            ann?.let {
                                val exists = mListAnnotatedMedhods.find {
                                    it.lowercase() == m.name.lowercase()
                                }
                                if (exists == null) {
                                    mListAnnotatedMedhods.add(m.name)
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            mListener?.onError(ex.message.toString())
        }
    }

    private fun findPackagMethods(stacktrace: Array<StackTraceElement>): List<String>? {
        val list = mutableListOf<String>()
        val seq_strack = stacktrace.toList()
        val filtered = seq_strack.filter {
            it.className.lowercase().contains(mContext.packageName.lowercase())
        }
        if (filtered.isNotEmpty()) {
            filtered.forEach { st ->
                val body =
                    "Class: ${st.className} Method: ${st.methodName} LineNumber<${st.lineNumber}>(${st.fileName})"
                list.add(body)
            }
            return list
        }
        return null
    }

    companion object {

        const val THREAD_TITLE = "[ ++ ANR Spy ++ ]"

    }

}