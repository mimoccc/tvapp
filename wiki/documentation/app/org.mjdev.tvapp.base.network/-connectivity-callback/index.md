//[app](../../../index.md)/[org.mjdev.tvapp.base.network](../index.md)/[ConnectivityCallback](index.md)

# ConnectivityCallback

[androidJvm]\
class [ConnectivityCallback](index.md)(val trySend: (data: [NetworkStatus](../-network-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [ConnectivityManager.NetworkCallback](https://developer.android.com/reference/kotlin/android/net/ConnectivityManager.NetworkCallback.html)

## Constructors

| | |
|---|---|
| [ConnectivityCallback](-connectivity-callback.md) | [androidJvm]<br>constructor(trySend: (data: [NetworkStatus](../-network-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [onAvailable](on-available.md) | [androidJvm]<br>open override fun [onAvailable](on-available.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onBlockedStatusChanged](index.md#1004516195%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onBlockedStatusChanged](index.md#1004516195%2FFunctions%2F-912451524)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [onCapabilitiesChanged](index.md#5611792%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onCapabilitiesChanged](index.md#5611792%2FFunctions%2F-912451524)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [NetworkCapabilities](https://developer.android.com/reference/kotlin/android/net/NetworkCapabilities.html)) |
| [onLinkPropertiesChanged](index.md#973932568%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onLinkPropertiesChanged](index.md#973932568%2FFunctions%2F-912451524)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [LinkProperties](https://developer.android.com/reference/kotlin/android/net/LinkProperties.html)) |
| [onLosing](index.md#-1693799552%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [onLosing](index.md#-1693799552%2FFunctions%2F-912451524)(p0: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html), p1: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onLost](on-lost.md) | [androidJvm]<br>open override fun [onLost](on-lost.md)(network: [Network](https://developer.android.com/reference/kotlin/android/net/Network.html)) |
| [onUnavailable](on-unavailable.md) | [androidJvm]<br>open override fun [onUnavailable](on-unavailable.md)() |

## Properties

| Name | Summary |
|---|---|
| [trySend](try-send.md) | [androidJvm]<br>val [trySend](try-send.md): (data: [NetworkStatus](../-network-status/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
