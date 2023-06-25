//[app](../../../index.md)/[org.mjdev.tvapp.api](../index.md)/[MovieAPI](index.md)

# MovieAPI

[androidJvm]\
class [MovieAPI](index.md)

## Constructors

| | |
|---|---|
| [MovieAPI](-movie-a-p-i.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [findMovieById](find-movie-by-id.md) | [androidJvm]<br>suspend fun [findMovieById](find-movie-by-id.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)): [Result](../../org.mjdev.tvapp.base.helpers/-result/index.md)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt; |
| [getMovieListByCategory](get-movie-list-by-category.md) | [androidJvm]<br>suspend fun [getMovieListByCategory](get-movie-list-by-category.md)(category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Result](../../org.mjdev.tvapp.base.helpers/-result/index.md)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt;&gt; |
| [loadCategoryList](load-category-list.md) | [androidJvm]<br>suspend fun [loadCategoryList](load-category-list.md)(): [Result](../../org.mjdev.tvapp.base.helpers/-result/index.md)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Category](../../org.mjdev.tvapp.data/-category/index.md)&gt;&gt; |
| [loadFeaturedMovieList](load-featured-movie-list.md) | [androidJvm]<br>suspend fun [loadFeaturedMovieList](load-featured-movie-list.md)(): [Result](../../org.mjdev.tvapp.base.helpers/-result/index.md)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt;&gt; |
