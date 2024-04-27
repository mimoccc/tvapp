//[app](../../../index.md)/[org.mjdev.tvapp.cast](../index.md)/[CastSessionTransferCallback](index.md)

# CastSessionTransferCallback

[androidJvm]\
class [CastSessionTransferCallback](index.md)(val onMediaTransferring: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onMediaTransferred: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sessionState: SessionState) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onMediaTransferFailed: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), transferFailedReason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : SessionTransferCallback

## Constructors

| | |
|---|---|
| [CastSessionTransferCallback](-cast-session-transfer-callback.md) | [androidJvm]<br>constructor(onMediaTransferring: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onMediaTransferred: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sessionState: SessionState) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onMediaTransferFailed: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), transferFailedReason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvapp.widget/-refresh-action/index.md#585090901%2FFunctions%2F-912451524)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1794629105%2FFunctions%2F-912451524)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onTransferFailed](on-transfer-failed.md) | [androidJvm]<br>open override fun [onTransferFailed](on-transfer-failed.md)(transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), transferFailedReason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onTransferred](on-transferred.md) | [androidJvm]<br>open override fun [onTransferred](on-transferred.md)(transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sessionState: SessionState) |
| [onTransferring](on-transferring.md) | [androidJvm]<br>open override fun [onTransferring](on-transferring.md)(transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvapp.widget/-refresh-action/index.md#1616463040%2FFunctions%2F-912451524)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [onMediaTransferFailed](on-media-transfer-failed.md) | [androidJvm]<br>val [onMediaTransferFailed](on-media-transfer-failed.md): (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), transferFailedReason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMediaTransferred](on-media-transferred.md) | [androidJvm]<br>val [onMediaTransferred](on-media-transferred.md): (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sessionState: SessionState) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMediaTransferring](on-media-transferring.md) | [androidJvm]<br>val [onMediaTransferring](on-media-transferring.md): (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
