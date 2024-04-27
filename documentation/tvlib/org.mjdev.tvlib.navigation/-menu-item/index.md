//[tvlib](../../../index.md)/[org.mjdev.tvlib.navigation](../index.md)/[MenuItem](index.md)

# MenuItem

[androidJvm]\
data class [MenuItem](index.md)(var menuText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, var menuIcon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, var menuGravity: [MenuItem.Gravity](-gravity/index.md) = Gravity.Center, var menuAction: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, var menuRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var menuPage: [Page](../../org.mjdev.tvlib.ui.components.page/-page/index.md)? = null, var enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true)

## Constructors

| | |
|---|---|
| [MenuItem](-menu-item.md) | [androidJvm]<br>constructor(menuText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, menuIcon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, menuGravity: [MenuItem.Gravity](-gravity/index.md) = Gravity.Center, menuAction: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, menuRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, menuPage: [Page](../../org.mjdev.tvlib.ui.components.page/-page/index.md)? = null, enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |
| [Gravity](-gravity/index.md) | [androidJvm]<br>enum [Gravity](-gravity/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[MenuItem.Gravity](-gravity/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [action](action.md) | [androidJvm]<br>fun [action](action.md)(action: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [MenuItem](index.md) |
| [component1](component1.md) | [androidJvm]<br>operator fun [component1](component1.md)(): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [component2](component2.md) | [androidJvm]<br>operator fun [component2](component2.md)(): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [component3](component3.md) | [androidJvm]<br>operator fun [component3](component3.md)(): [MenuItem.Gravity](-gravity/index.md) |
| [component4](component4.md) | [androidJvm]<br>operator fun [component4](component4.md)(): () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? |
| [component5](component5.md) | [androidJvm]<br>operator fun [component5](component5.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [component6](component6.md) | [androidJvm]<br>operator fun [component6](component6.md)(): [Page](../../org.mjdev.tvlib.ui.components.page/-page/index.md)? |
| [component7](component7.md) | [androidJvm]<br>operator fun [component7](component7.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [copy](copy.md) | [androidJvm]<br>fun [copy](copy.md)(menuText: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, menuIcon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, menuGravity: [MenuItem.Gravity](-gravity/index.md) = Gravity.Center, menuAction: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, menuRoute: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, menuPage: [Page](../../org.mjdev.tvlib.ui.components.page/-page/index.md)? = null, enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true): [MenuItem](index.md) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator override fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [gravity](gravity.md) | [androidJvm]<br>fun [gravity](gravity.md)(gravity: [MenuItem.Gravity](-gravity/index.md)): [MenuItem](index.md) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [icon](icon.md) | [androidJvm]<br>fun [icon](icon.md)(icon: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [MenuItem](index.md) |
| [text](text.md) | [androidJvm]<br>fun [text](text.md)(text: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [MenuItem](index.md) |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [enabled](enabled.md) | [androidJvm]<br>var [enabled](enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isAction](is-action.md) | [androidJvm]<br>val [isAction](is-action.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isEnabled](is-enabled.md) | [androidJvm]<br>val [isEnabled](is-enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isPage](is-page.md) | [androidJvm]<br>val [isPage](is-page.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isRoute](is-route.md) | [androidJvm]<br>val [isRoute](is-route.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [menuAction](menu-action.md) | [androidJvm]<br>var [menuAction](menu-action.md): () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? |
| [menuGravity](menu-gravity.md) | [androidJvm]<br>var [menuGravity](menu-gravity.md): [MenuItem.Gravity](-gravity/index.md) |
| [menuIcon](menu-icon.md) | [androidJvm]<br>var [menuIcon](menu-icon.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [menuPage](menu-page.md) | [androidJvm]<br>var [menuPage](menu-page.md): [Page](../../org.mjdev.tvlib.ui.components.page/-page/index.md)? |
| [menuRoute](menu-route.md) | [androidJvm]<br>var [menuRoute](menu-route.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [menuText](menu-text.md) | [androidJvm]<br>var [menuText](menu-text.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
