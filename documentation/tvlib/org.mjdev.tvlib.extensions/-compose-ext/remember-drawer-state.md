//[tvlib](../../../index.md)/[org.mjdev.tvlib.extensions](../index.md)/[ComposeExt](index.md)/[rememberDrawerState](remember-drawer-state.md)

# rememberDrawerState

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [rememberDrawerState](remember-drawer-state.md)(initialValue: [DrawerValue](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerValue.html) = if (isEditMode()) DrawerValue.Open else DrawerValue.Closed): [DrawerState](https://developer.android.com/reference/kotlin/androidx/tv/material3/DrawerState.html)
