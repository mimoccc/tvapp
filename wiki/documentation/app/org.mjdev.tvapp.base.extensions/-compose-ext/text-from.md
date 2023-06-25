//[app](../../../index.md)/[org.mjdev.tvapp.base.extensions](../index.md)/[ComposeExt](index.md)/[textFrom](text-from.md)

# textFrom

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

inline fun &lt;[T](text-from.md)&gt; [textFrom](text-from.md)(text: [T](text-from.md)?): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Helper function to return text from any object is given as input. Mainly: Int -> is represented as resource text id String -> String null -> empty string other -> toString() call result

#### Return

String generated from any resource given

#### Parameters

androidJvm

| | |
|---|---|
| text | Text |
| T | T type of result |
