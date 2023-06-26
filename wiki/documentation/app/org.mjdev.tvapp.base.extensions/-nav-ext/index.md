//[app](../../../index.md)/[org.mjdev.tvapp.base.extensions](../index.md)/[NavExt](index.md)

# NavExt

[androidJvm]\
object [NavExt](index.md)

## Functions

| Name | Summary |
|---|---|
| [createGraph](create-graph.md) | [androidJvm]<br>inline fun [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html).[createGraph](create-graph.md)(route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), builder: [NavGraphBuilderEx](../../org.mjdev.tvapp.base.navigation/-nav-graph-builder-ex/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavGraph](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraph.html)<br>Create navigation graph from nav controller. Extended just with custom builder. |
| [navigation](navigation.md) | [androidJvm]<br>inline fun [NavigatorProvider](https://developer.android.com/reference/kotlin/androidx/navigation/NavigatorProvider.html).[navigation](navigation.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), route: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, builder: [NavGraphBuilderEx](../../org.mjdev.tvapp.base.navigation/-nav-graph-builder-ex/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [NavGraph](https://developer.android.com/reference/kotlin/androidx/navigation/NavGraph.html)<br>Create navigation from extra builder to simplify generation of routes. |
