//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.page](index.md)/[TvPager](-tv-pager.md)

# TvPager

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TvPager](-tv-pager.md)(startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, onPageChange: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), page: [Page](-page/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { index, page -&gt; }, navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md) = rememberNavControllerEx(), pages: [PagerScope](-pager-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { page(EMPTY_PAGE) })
