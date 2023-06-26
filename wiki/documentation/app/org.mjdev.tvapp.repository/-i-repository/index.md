//[app](../../../index.md)/[org.mjdev.tvapp.repository](../index.md)/[IRepository](index.md)

# IRepository

interface [IRepository](index.md)

#### Inheritors

| |
|---|
| [MovieRepository](../-movie-repository/index.md) |

## Functions

| Name | Summary |
|---|---|
| [findMovieById](find-movie-by-id.md) | [androidJvm]<br>abstract suspend fun [findMovieById](find-movie-by-id.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?): [Movie](../../org.mjdev.tvapp.data/-movie/index.md)? |
| [getCategoryList](get-category-list.md) | [androidJvm]<br>abstract suspend fun [getCategoryList](get-category-list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Category](../../org.mjdev.tvapp.data/-category/index.md)&gt; |
| [getFeaturedMovieList](get-featured-movie-list.md) | [androidJvm]<br>abstract suspend fun [getFeaturedMovieList](get-featured-movie-list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt; |
