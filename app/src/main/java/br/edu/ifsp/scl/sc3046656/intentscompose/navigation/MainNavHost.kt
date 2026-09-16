package br.edu.ifsp.scl.sc3046656.intentscompose.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3046656.intentscompose.composable.AddWordScreen
import br.edu.ifsp.scl.sc3046656.intentscompose.composable.HomeScreen

private const val PALAVRA_NOVA = "PALAVRA_NOVA"
private const val STRING_ATUAL = "stringAtual"

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) { backStackEntry ->
            val palavraDevolvida by backStackEntry.savedStateHandle
                .getStateFlow(PALAVRA_NOVA, "")
                .collectAsState()

            HomeScreen(
                palavraRecebida = palavraDevolvida,
                modifier = modifier,
                onAddWordClick = { stringAtual ->
                    navHostController.navigate(
                        "${Screen.AddWordScreen.route}?$STRING_ATUAL=${Uri.encode(stringAtual)}"
                    )
                }
            )
        }

        composable(
            route = "${Screen.AddWordScreen.route}?$STRING_ATUAL={$STRING_ATUAL}",
            arguments = listOf(
                navArgument(STRING_ATUAL) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val stringRecebida = backStackEntry.arguments?.getString(STRING_ATUAL) ?: ""

            AddWordScreen(
                stringRecebida = stringRecebida,
                modifier = modifier,
                onConcatenarClick = { palavra ->
                    navHostController.previousBackStackEntry
                        ?.savedStateHandle?.set(PALAVRA_NOVA, palavra)
                    navHostController.popBackStack()
                }
            )
        }
    }
}