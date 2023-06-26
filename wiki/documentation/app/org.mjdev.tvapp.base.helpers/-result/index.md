//[app](../../../index.md)/[org.mjdev.tvapp.base.helpers](../index.md)/[Result](index.md)

# Result

sealed class [Result](index.md)&lt;out [T](index.md)&gt;

#### Inheritors

| |
|---|
| [Empty](-empty/index.md) |
| [Success](-success/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |
| [Empty](-empty/index.md) | [androidJvm]<br>object [Empty](-empty/index.md) : [Result](index.md)&lt;[Nothing](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-nothing/index.html)&gt; |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)&lt;[T](-success/index.md)&gt;(val value: [T](-success/index.md)) : [Result](index.md)&lt;[T](-success/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [unwrapOr](-companion/unwrap-or.md) | [androidJvm]<br>fun &lt;[T](-companion/unwrap-or.md)&gt; [Result](index.md)&lt;[T](-companion/unwrap-or.md)&gt;.[unwrapOr](-companion/unwrap-or.md)(defaultValue: [T](-companion/unwrap-or.md)): [T](-companion/unwrap-or.md) |
