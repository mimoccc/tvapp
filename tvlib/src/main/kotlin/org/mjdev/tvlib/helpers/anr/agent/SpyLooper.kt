package org.mjdev.tvlib.helpers.anr.agent

import android.annotation.SuppressLint
import android.os.Looper
import android.os.Message
import android.os.MessageQueue
import timber.log.Timber
import java.lang.reflect.Field

@SuppressLint("PrivateApi")
class SpyLooper {

    var messagesField: Field
    var nextField: Field
    var mainMessageQueue: MessageQueue

    init {
        try {
            val queueField = Looper::class.java.getDeclaredField("mQueue")
            queueField.isAccessible = true
            messagesField = MessageQueue::class.java.getDeclaredField("mMessages")
            messagesField.isAccessible = true
            nextField = Message::class.java.getDeclaredField("next")
            nextField.isAccessible = true
            val mainLooper = Looper.getMainLooper()
            mainMessageQueue = queueField[mainLooper] as MessageQueue
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun dumpQueue() {
        try {
            val nextMessage = messagesField[mainMessageQueue] as Message
            Timber.d("Begin dumping queue")
            dumpMessages(nextMessage)
            Timber.d("End dumping queue")
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    @Throws(IllegalAccessException::class)
    fun dumpMessages(message: Message?) {
        message?.let {
            Timber.w("Message: $message")
            val next = nextField[message] as Message?
            dumpMessages(next)
        }
    }

}