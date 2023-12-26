//[app](../../../../index.md)/[org.mjdev.tvapp.database](../../index.md)/[DAO](../index.md)/[Companion](index.md)

# Companion

[androidJvm]\
object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [findById](find-by-id.md) | [androidJvm]<br>inline fun &lt;[T](find-by-id.md)&gt; Box&lt;[T](find-by-id.md)&gt;.[findById](find-by-id.md)(id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?): [T](find-by-id.md)? |
| [property](property.md) | [androidJvm]<br>inline fun &lt;[T](property.md)&gt; [T](property.md).[property](property.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [tx](tx.md) | [androidJvm]<br>inline suspend fun &lt;[V](tx.md), [T](tx.md)&gt; Box&lt;[T](tx.md)&gt;.[tx](tx.md)(crossinline transaction: Box&lt;[T](tx.md)&gt;.() -&gt; [V](tx.md)): [V](tx.md)? |
| [with](with.md) | [androidJvm]<br>inline fun &lt;[T](with.md)&gt; [with](with.md)(receiver: [T](with.md), block: [T](with.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [T](with.md) |

## Properties

| Name | Summary |
|---|---|
| [DATABASE_NAME](-d-a-t-a-b-a-s-e_-n-a-m-e.md) | [androidJvm]<br>val [DATABASE_NAME](-d-a-t-a-b-a-s-e_-n-a-m-e.md): &lt;Error class: unknown class&gt; |
