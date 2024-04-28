//[tvlib](../../../index.md)/[org.mjdev.tvlib.viewmodel](../index.md)/[BaseViewModel](index.md)/[stateInViewModel](state-in-view-model.md)

# stateInViewModel

[androidJvm]\
fun &lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt; Flow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt;&gt;.[stateInViewModel](state-in-view-model.md)(initial: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt; = mapOf()): StateFlow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](state-in-view-model.md), [V](state-in-view-model.md)&gt;&gt;

fun &lt;[T](state-in-view-model.md)&gt; Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt;&gt;.[stateInViewModel](state-in-view-model.md)(initial: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt; = listOf()): StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](state-in-view-model.md)&gt;&gt;

fun &lt;[T](state-in-view-model.md)&gt; Flow&lt;[T](state-in-view-model.md)&gt;.[stateInViewModel](state-in-view-model.md)(initial: [T](state-in-view-model.md)): StateFlow&lt;[T](state-in-view-model.md)&gt;
