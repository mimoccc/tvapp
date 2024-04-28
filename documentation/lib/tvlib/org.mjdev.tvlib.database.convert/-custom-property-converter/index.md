//[tvlib](../../../index.md)/[org.mjdev.tvlib.database.convert](../index.md)/[CustomPropertyConverter](index.md)

# CustomPropertyConverter

[androidJvm]\
open class [CustomPropertyConverter](index.md)&lt;[T](index.md) : [ConvertEntity](../-convert-entity/index.md)&gt;(supplier: (id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) -&gt; [T](index.md)?) : PropertyConverter&lt;[T](index.md), [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)&gt;

## Constructors

| | |
|---|---|
| [CustomPropertyConverter](-custom-property-converter.md) | [androidJvm]<br>constructor(supplier: (id: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)) -&gt; [T](index.md)?) |

## Functions

| Name | Summary |
|---|---|
| [convertToDatabaseValue](convert-to-database-value.md) | [androidJvm]<br>open override fun [convertToDatabaseValue](convert-to-database-value.md)(entityProperty: [T](index.md)): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [convertToEntityProperty](convert-to-entity-property.md) | [androidJvm]<br>open override fun [convertToEntityProperty](convert-to-entity-property.md)(databaseValue: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)?): [T](index.md)? |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
