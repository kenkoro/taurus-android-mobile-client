package com.kenkoro.taurus.client.feature.sewing.presentation.util

sealed class Screen(val route: String) {
  data object WelcomeScreen : Screen("welcome_screen")

  data object LoginScreen : Screen("login_screen")

  data object DashboardScreen : Screen("dashboard_screen")
}