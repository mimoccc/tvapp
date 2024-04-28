//[tvlib](../../index.md)/[org.mjdev.tvlib.auth](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [AuthManager](-auth-manager/index.md) | [androidJvm]<br>class [AuthManager](-auth-manager/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val scheme: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_scheme), val clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_client_id), val domain: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_domain), val allowedPackages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = listOf(), val onUserChange: [AuthManager](-auth-manager/index.md).(user: [User](../org.mjdev.tvlib.data.local/-user/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null) |
