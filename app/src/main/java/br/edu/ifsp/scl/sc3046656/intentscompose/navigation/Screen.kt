package br.edu.ifsp.scl.sc3046656.intentscompose.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object AddWordScreen : Screen("add_word_screen")
}