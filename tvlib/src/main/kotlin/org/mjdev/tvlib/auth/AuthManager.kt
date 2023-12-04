/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.auth

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.jwt.JWT
import com.auth0.android.provider.BrowserPicker
import com.auth0.android.provider.CustomTabsOptions
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import org.mjdev.tvlib.R
import org.mjdev.tvlib.data.local.User
import timber.log.Timber

@Suppress("unused")
class AuthManager(
    val context: Context,
    val scheme: String = context.getString(R.string.com_auth0_scheme),
    val clientId: String = context.getString(R.string.com_auth0_client_id),
    val domain: String = context.getString(R.string.com_auth0_domain),
    val allowedPackages: List<String> = listOf(), // BuildConfig.APPLICATION_ID
    val onUserChange: AuthManager.(user: User?) -> Unit = {}
) {

    var idToken: String? = null
    val isUserLoggedIn: Boolean get() = ((idToken != null) && (user != null))
    private val account: Auth0 by lazy { Auth0(clientId, domain) }

    val user: User?
        get() = try {
            JWT(idToken.toString()).let { jwt ->
                User().apply {
                    sub = jwt.subject ?: ""
                    fullName = jwt.getClaim("name").asString() ?: ""
                    email = jwt.getClaim("email").asString() ?: ""
                    emailVerified = jwt.getClaim("email_verified").asBoolean() ?: false
                    pictureUrl = jwt.getClaim("picture").asString() ?: ""
                    lastUpdated = jwt.getClaim("updated_at").asString() ?: ""
                    givenName = jwt.getClaim("given_name").asString() ?: ""
                    familyName = jwt.getClaim("family_name").asString() ?: ""
                    nickname = jwt.getClaim("nickname").asString() ?: ""
                    locale = jwt.getClaim("locale").asString() ?: "en"
                }
            }
        } catch (e: Exception) {
            // todo bad token
            Timber.e(e)
            null
        }

    init {
        idToken = context
            .getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE)
            .getString(SAVE_KEY, null)
        onUserChange(this, user)
    }

    private fun saveToken() {
        context.getSharedPreferences(SAVE_KEY, Context.MODE_PRIVATE).also { sp ->
            sp.edit().putString(SAVE_KEY, idToken).apply()
        }
    }

    fun login() {
        WebAuthProvider
            .login(account)
            .withScheme(scheme)
            .withTrustedWebActivity()
            .withCustomTabsOptions(
                CustomTabsOptions.newBuilder()
                    .showTitle(false)
                    .withBrowserPicker(
                        BrowserPicker.newBuilder()
                            .withAllowedPackages(allowedPackages)
                            .build()
                    )
                    .build()
            )
            .start(
                context,
                object : Callback<Credentials, AuthenticationException> {
                    override fun onFailure(error: AuthenticationException) {
                        idToken = ""
                        saveToken()
                        onUserChange(this@AuthManager, user)
                        Timber.e("Error occurred in login(): $error")
                    }

                    override fun onSuccess(result: Credentials) {
                        idToken = result.idToken
                        saveToken()
                        onUserChange(this@AuthManager, user)
                        Timber.d("User successfully logged in.")
                    }
                }
            )
    }

    fun logout() {
        WebAuthProvider
            .logout(account)
            .withScheme(scheme)
            .withTrustedWebActivity()
            .withCustomTabsOptions(
                CustomTabsOptions.newBuilder()
                    .showTitle(false)
                    .withBrowserPicker(
                        BrowserPicker.newBuilder()
                            .withAllowedPackages(allowedPackages)
                            .build()
                    )
                    .build()
            )
            .start(
                context,
                object : Callback<Void?, AuthenticationException> {
                    override fun onFailure(error: AuthenticationException) {
                        Timber.e("Error occurred in logout(): $error")
                    }

                    override fun onSuccess(result: Void?) {
                        idToken = ""
                        saveToken()
                        onUserChange(this@AuthManager, user)
                    }
                }
            )
    }

    companion object {

        private const val SAVE_KEY = "AuthManager"

        @SuppressLint("RememberReturnType", "ComposableNaming")
        @Composable
        fun rememberAuthManager(
            scheme: String? = null,
            clientId: String? = null,
            domain: String? = null,
            allowedPackages: List<String> = listOf(),
            onUserChange: AuthManager.(user: User?) -> Unit = {}
        ) = LocalContext.current.let { ctx ->
            remember(ctx) {
                AuthManager(
                    context = ctx,
                    scheme = scheme ?: ctx.getString(R.string.com_auth0_scheme),
                    clientId = clientId ?: ctx.getString(R.string.com_auth0_client_id),
                    domain = domain ?: ctx.getString(R.string.com_auth0_domain),
                    allowedPackages = allowedPackages,
                    onUserChange = onUserChange
                )
            }
        }

    }

}