@file:Suppress("unused")

package org.mjdev.tvlib.ui.toolkit.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AccessibilityManager
import androidx.compose.ui.platform.LocalAccessibilityManager
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

enum class SnackbarResult {
    Dismissed,
    ActionPerformed,
}

enum class SnackbarDuration {
    Short,
    Long,
    Indefinite
}

@Stable
interface Data {
    val accent: Color
    val leading: Any?

    val message: CharSequence
    val action: CharSequence?
    val duration: SnackbarDuration
    val withDismissAction: Boolean

    fun action()
    fun dismiss()
}

@Stable
private class DataImpl(
    override val message: CharSequence,
    override val action: CharSequence?,
    override val accent: Color,
    override val leading: Any?,
    override val duration: SnackbarDuration,
    override val withDismissAction: Boolean,
    private val continuation: CancellableContinuation<SnackbarResult>,
) : Data {
    override fun action() {
        if (continuation.isActive) continuation.resume(SnackbarResult.ActionPerformed)
    }

    override fun dismiss() {
        if (continuation.isActive) continuation.resume(SnackbarResult.Dismissed)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as DataImpl
        if (accent != other.accent) return false
        if (leading != other.leading) return false
        if (message != other.message) return false
        if (action != other.action) return false
        if (duration != other.duration) return false
        if (withDismissAction != other.withDismissAction) return false
        return continuation == other.continuation
    }

    override fun hashCode(): Int {
        var result = accent.hashCode()
        result = 31 * result + (leading?.hashCode() ?: 0)
        result = 31 * result + message.hashCode()
        result = 31 * result + (action?.hashCode() ?: 0)
        result = 31 * result + duration.hashCode()
        result = 31 * result + withDismissAction.hashCode()
        result = 31 * result + continuation.hashCode()
        return result
    }
}

// TODO: magic numbers adjustment
internal fun SnackbarDuration.toMillis(
    hasAction: Boolean,
    accessibilityManager: AccessibilityManager?
): Long {
    val original = when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
    if (accessibilityManager == null) {
        return original
    }
    return accessibilityManager.calculateRecommendedTimeoutMillis(
        original,
        containsIcons = true,
        containsText = true,
        containsControls = hasAction
    )
}

@Stable
class SnackbarHostState {
    private val mutex = Mutex()

    var currentSnackbarData by mutableStateOf<Data?>(null)
        private set

    suspend fun showSnackbar(
        msg: CharSequence,
        action: CharSequence? = null,
        leading: Any? = null,
        accent: Color = Color.Unspecified,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = if (action == null) SnackbarDuration.Short else SnackbarDuration.Indefinite
    ): SnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentSnackbarData =
                    DataImpl(
                        msg,
                        action,
                        accent,
                        leading,
                        duration,
                        withDismissAction,
                        continuation
                    )
            }
        } finally {
            currentSnackbarData = null
        }
    }
}

@Composable
fun SnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (Data) -> Unit
) {
    val currentSnackbarData = hostState.currentSnackbarData
    val accessibilityManager = LocalAccessibilityManager.current
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = currentSnackbarData.duration.toMillis(
                currentSnackbarData.action != null,
                accessibilityManager
            )
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    FadeInFadeOutWithScale(
        current = hostState.currentSnackbarData,
        modifier = modifier,
        content = snackbar
    )
}
