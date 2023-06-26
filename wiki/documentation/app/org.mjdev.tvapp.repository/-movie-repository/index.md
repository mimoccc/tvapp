//[app](../../../index.md)/[org.mjdev.tvapp.repository](../index.md)/[MovieRepository](index.md)

# MovieRepository

[androidJvm]\
class [MovieRepository](index.md)@Injectconstructor(dataSource: [MovieAPI](../../org.mjdev.tvapp.api/-movie-a-p-i/index.md)) : [IRepository](../-i-repository/index.md)

## Constructors

| | |
|---|---|
| [MovieRepository](-movie-repository.md) | [androidJvm]<br>@Inject<br>constructor(dataSource: [MovieAPI](../../org.mjdev.tvapp.api/-movie-a-p-i/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [findMovieById](find-movie-by-id.md) | [androidJvm]<br>open suspend override fun [findMovieById](find-movie-by-id.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?): [Movie](../../org.mjdev.tvapp.data/-movie/index.md)? |
| [getCategoryList](get-category-list.md) | [androidJvm]<br>open suspend override fun [getCategoryList](get-category-list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Category](../../org.mjdev.tvapp.data/-category/index.md)&gt; |
| [getFeaturedMovieList](get-featured-movie-list.md) | [androidJvm]<br>open suspend override fun [getFeaturedMovieList](get-featured-movie-list.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt; |
| [getMovieListByCategory](get-movie-list-by-category.md) | [androidJvm]<br>suspend fun [getMovieListByCategory](get-movie-list-by-category.md)(category: [Category](../../org.mjdev.tvapp.data/-category/index.md)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Movie](../../org.mjdev.tvapp.data/-movie/index.md)&gt; |
