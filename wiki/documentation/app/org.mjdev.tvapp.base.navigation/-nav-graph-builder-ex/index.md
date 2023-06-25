//[app](../../../index.md)/[org.mjdev.tvapp.base.navigation](../index.md)/[NavGraphBuilderEx](index.md)

# NavGraphBuilderEx

class [NavGraphBuilderEx](index.md)(provider: [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html), route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, val navHostController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html)) : [NavGraphBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraphBuilder.html)

Custom nav graph builder.

Just extended of [navHostController](nav-host-controller.md) for simplify navigation auto creation.

#### Parameters

androidJvm

| |
|---|
| provider |
| route |

#### See also

| | |
|---|---|
|  | : [androidx.navigation.NavGraphBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraphBuilder.html) |

## Constructors

| | |
|---|---|
| [NavGraphBuilderEx](-nav-graph-builder-ex.md) | [androidJvm]<br>constructor(provider: [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html), route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, navHostController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html))<br>Create [NavGraphBuilderEx](index.md) |

## Functions

| Name | Summary |
|---|---|
| [action](index.md#-1754249251%2FFunctions%2F-912451524) | [androidJvm]<br>fun [~~action~~](index.md#-1754249251%2FFunctions%2F-912451524)(actionId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), actionBuilder: [NavActionBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavActionBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [addDestination](index.md#2018986491%2FFunctions%2F-912451524) | [androidJvm]<br>fun [addDestination](index.md#2018986491%2FFunctions%2F-912451524)(destination: [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html)) |
| [argument](index.md#-438696563%2FFunctions%2F-912451524) | [androidJvm]<br>fun [argument](index.md#-438696563%2FFunctions%2F-912451524)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), argumentBuilder: [NavArgumentBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavArgumentBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |
| [build](build.md) | [androidJvm]<br>open override fun [build](build.md)(): [NavGraph](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraph.html) |
| [deepLink](index.md#-1156471893%2FFunctions%2F-912451524) | [androidJvm]<br>fun [deepLink](index.md#-1156471893%2FFunctions%2F-912451524)(navDeepLink: [NavDeepLinkDslBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavDeepLinkDslBuilder.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>fun [deepLink](index.md#-150036510%2FFunctions%2F-912451524)(uriPattern: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [destination](index.md#-1004337867%2FFunctions%2F-912451524) | [androidJvm]<br>fun &lt;[D](index.md#-1004337867%2FFunctions%2F-912451524) : [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html)&gt; [destination](index.md#-1004337867%2FFunctions%2F-912451524)(navDestination: [NavDestinationBuilder](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestinationBuilder.html)&lt;[D](index.md#-1004337867%2FFunctions%2F-912451524)&gt;) |
| [screen](../-screen/-companion/screen.md) | [androidJvm]<br>fun &lt;[T](../-screen/-companion/screen.md) : [Screen](../-screen/index.md)&gt; [NavGraphBuilderEx](index.md).[screen](../-screen/-companion/screen.md)(route: [T](../-screen/-companion/screen.md), isHomeScreen: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false)<br>Custom function to generate screen in nav graph. |
| [unaryPlus](index.md#-837558775%2FExtensions%2F-912451524) | [androidJvm]<br>operator fun [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html).[unaryPlus](index.md#-837558775%2FExtensions%2F-912451524)() |

## Properties

| Name | Summary |
|---|---|
| [id](index.md#-1474937811%2FProperties%2F-912451524) | [androidJvm]<br>val [id](index.md#-1474937811%2FProperties%2F-912451524): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [label](index.md#-353711554%2FProperties%2F-912451524) | [androidJvm]<br>var [label](index.md#-353711554%2FProperties%2F-912451524): [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)? |
| [menuItems](menu-items.md) | [androidJvm]<br>var [menuItems](menu-items.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[MenuItem](../-menu-item/index.md)&gt; |
| [navHostController](nav-host-controller.md) | [androidJvm]<br>val [navHostController](nav-host-controller.md): [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html) |
| [provider](index.md#1416870999%2FProperties%2F-912451524) | [androidJvm]<br>val [provider](index.md#1416870999%2FProperties%2F-912451524): [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html) |
| [route](index.md#1604817033%2FProperties%2F-912451524) | [androidJvm]<br>val [route](index.md#1604817033%2FProperties%2F-912451524): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [startDestinationRouteEx](start-destination-route-ex.md) | [androidJvm]<br>var [startDestinationRouteEx](start-destination-route-ex.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
