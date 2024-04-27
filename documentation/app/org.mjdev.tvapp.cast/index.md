//[app](../../index.md)/[org.mjdev.tvapp.cast](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [CastOptionsProvider](-cast-options-provider/index.md) | [androidJvm]<br>class [CastOptionsProvider](-cast-options-provider/index.md) : OptionsProvider |
| [CastService](-cast-service/index.md) | [androidJvm]<br>class [CastService](-cast-service/index.md) : [Service](https://developer.android.com/reference/kotlin/android/app/Service.html), SessionManagerListener&lt;CastSession&gt; |
| [CastSessionTransferCallback](-cast-session-transfer-callback/index.md) | [androidJvm]<br>class [CastSessionTransferCallback](-cast-session-transfer-callback/index.md)(val onMediaTransferring: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onMediaTransferred: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), sessionState: SessionState) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), val onMediaTransferFailed: (transferType: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), transferFailedReason: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : SessionTransferCallback |
