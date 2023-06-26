//[app](../../../index.md)/[org.mjdev.tvapp.module](../index.md)/[ProvideModule](index.md)

# ProvideModule

[androidJvm]\
@Module

class [ProvideModule](index.md)

## Constructors

| | |
|---|---|
| [ProvideModule](-provide-module.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [providesMovieAPI](provides-movie-a-p-i.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesMovieAPI](provides-movie-a-p-i.md)(): [MovieAPI](../../org.mjdev.tvapp.api/-movie-a-p-i/index.md) |
| [providesMovieRepository](provides-movie-repository.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesMovieRepository](provides-movie-repository.md)(movieApi: [MovieAPI](../../org.mjdev.tvapp.api/-movie-a-p-i/index.md)): [IRepository](../../org.mjdev.tvapp.repository/-i-repository/index.md) |
| [providesNetworkConnectivityService](provides-network-connectivity-service.md) | [androidJvm]<br>@Singleton<br>@Provides<br>fun [providesNetworkConnectivityService](provides-network-connectivity-service.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [NetworkConnectivityService](../../org.mjdev.tvapp.base.network/-network-connectivity-service/index.md) |
