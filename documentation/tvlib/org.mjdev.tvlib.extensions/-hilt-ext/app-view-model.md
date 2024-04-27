//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[HiltExt](index.md)/[appViewModel](app-view-model.md)

# appViewModel

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

inline fun &lt;[VM](app-view-model.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)&gt; [appViewModel](app-view-model.md)(viewModelStoreOwner: [ViewModelStoreOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModelStoreOwner.html)? = null, key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, mockFnName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = String.format(&quot;mock%s&quot;, VM::class.simpleName), companion: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)&lt;*&gt;? = VM::class.companionObject, noinline modelMockFnc: (context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) -&gt; [VM](app-view-model.md)? = { context -&gt;
            companion?.functions?.firstOrNull { fn -&gt;
                fn.name == mockFnName
            }?.call(companion, context) as VM
        }): [VM](app-view-model.md)
