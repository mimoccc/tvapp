//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.page](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Page](-page/index.md) | [androidJvm]<br>open class [Page](-page/index.md) |
| [PagerScope](-pager-scope/index.md) | [androidJvm]<br>class [PagerScope](-pager-scope/index.md)(val navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md), startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, pages: [PagerScope](-pager-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, val onPageChange: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), page: [Page](-page/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { index, page -&gt; }) : [ArrayList](https://developer.android.com/reference/kotlin/java/util/ArrayList.html)&lt;[Page](-page/index.md)&gt; |
| [SearchPage](-search-page/index.md) | [androidJvm]<br>open class [SearchPage](-search-page/index.md) : [Page](-page/index.md) |

## Functions

| Name | Summary |
|---|---|
| [rememberPagerScope](remember-pager-scope.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [rememberPagerScope](remember-pager-scope.md)(navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md), startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, pages: [PagerScope](-pager-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onPageChange: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), page: [Page](-page/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { index, page -&gt; }): [PagerScope](-pager-scope/index.md) |
| [TvPager](-tv-pager.md) | [androidJvm]<br>@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)<br>fun [TvPager](-tv-pager.md)(startIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, onPageChange: (index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), page: [Page](-page/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { index, page -&gt; }, navController: [NavHostControllerEx](../org.mjdev.tvlib.navigation/-nav-host-controller-ex/index.md) = rememberNavControllerEx(), pages: [PagerScope](-pager-scope/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { page(EMPTY_PAGE) }) |
