//[tvlib](../../../index.md)/[org.mjdev.tvlib.auth](../index.md)/[AuthManager](index.md)

# AuthManager

[androidJvm]\
class [AuthManager](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val scheme: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_scheme), val clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_client_id), val domain: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_domain), val allowedPackages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = listOf(), val onUserChange: [AuthManager](index.md).(user: [User](../../org.mjdev.tvlib.data.local/-user/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)

## Constructors

| | |
|---|---|
| [AuthManager](-auth-manager.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), scheme: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_scheme), clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_client_id), domain: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_domain), allowedPackages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = listOf(), onUserChange: [AuthManager](index.md).(user: [User](../../org.mjdev.tvlib.data.local/-user/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [login](login.md) | [androidJvm]<br>fun [login](login.md)() |
| [logout](logout.md) | [androidJvm]<br>fun [logout](logout.md)() |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [allowedPackages](allowed-packages.md) | [androidJvm]<br>val [allowedPackages](allowed-packages.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [clientId](client-id.md) | [androidJvm]<br>val [clientId](client-id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [domain](domain.md) | [androidJvm]<br>val [domain](domain.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [idToken](id-token.md) | [androidJvm]<br>var [idToken](id-token.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [isUserLoggedIn](is-user-logged-in.md) | [androidJvm]<br>val [isUserLoggedIn](is-user-logged-in.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onUserChange](on-user-change.md) | [androidJvm]<br>val [onUserChange](on-user-change.md): [AuthManager](index.md).(user: [User](../../org.mjdev.tvlib.data.local/-user/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null |
| [scheme](scheme.md) | [androidJvm]<br>val [scheme](scheme.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [user](user.md) | [androidJvm]<br>val [user](user.md): [User](../../org.mjdev.tvlib.data.local/-user/index.md)? |
