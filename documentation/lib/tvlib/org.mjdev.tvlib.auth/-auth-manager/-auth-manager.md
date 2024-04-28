//[tvlib](../../../index.md)/[org.mjdev.tvlib.auth](../index.md)/[AuthManager](index.md)/[AuthManager](-auth-manager.md)

# AuthManager

[androidJvm]\
constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), scheme: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_scheme), clientId: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_client_id), domain: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = context.getString(R.string.com_auth0_domain), allowedPackages: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; = listOf(), onUserChange: [AuthManager](index.md).(user: [User](../../org.mjdev.tvlib.data.local/-user/index.md)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
