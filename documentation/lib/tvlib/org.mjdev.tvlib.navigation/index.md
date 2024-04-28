//[tvlib](../../index.md)/[org.mjdev.tvlib.navigation](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AnyType](-any-type/index.md) | [androidJvm]<br>object [AnyType](-any-type/index.md) : [NavType](https://developer.android.com/reference/kotlin/androidx/navigation/NavType.html)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; |
| [MenuItem](-menu-item/index.md) | [androidJvm]<br>data class [MenuItem](-menu-item/index.md)(var menuText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, var menuIcon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, var menuGravity: [MenuItem.Gravity](-menu-item/-gravity/index.md) = Gravity.Center, var menuAction: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, var menuRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var menuPage: [Page](../org.mjdev.tvlib.ui.components.page/-page/index.md)? = null, var enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |
| [MenuItemClickListener](-menu-item-click-listener/index.md) | [androidJvm]<br>interface [MenuItemClickListener](-menu-item-click-listener/index.md) |
| [NavGraphBuilderEx](-nav-graph-builder-ex/index.md) | [androidJvm]<br>class [NavGraphBuilderEx](-nav-graph-builder-ex/index.md)(provider: [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html), route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, startRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, val navHostController: [NavHostControllerEx](-nav-host-controller-ex/index.md)) : [NavGraphBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraphBuilder.html) |
| [NavHostControllerEx](-nav-host-controller-ex/index.md) | [androidJvm]<br>class [NavHostControllerEx](-nav-host-controller-ex/index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), [LifecycleOwner](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleOwner.html), [DefaultLifecycleObserver](https://developer.android.com/reference/kotlin/androidx/lifecycle/DefaultLifecycleObserver.html) |
