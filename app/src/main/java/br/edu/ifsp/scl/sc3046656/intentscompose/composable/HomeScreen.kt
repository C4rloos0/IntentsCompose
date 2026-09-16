package br.edu.ifsp.scl.sc3046656.intentscompose.composable

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3046656.intentscompose.ui.theme.IntentsComposeTheme

@Composable
fun HomeScreen(
    palavraRecebida : String,
    modifier: Modifier = Modifier,
    onAddWordClick: (String) -> Unit,

){
    var stringAtual by rememberSaveable {mutableStateOf("")}

    LaunchedEffect(palavraRecebida) {
        if(palavraRecebida.isNotEmpty()){
            stringAtual = if (stringAtual.isEmpty()){
                palavraRecebida
            } else{
                "$stringAtual $palavraRecebida"
            }
        }
    }

    Column(modifier = modifier.fillMaxWidth()){
        OutlinedTextField(
            value = stringAtual,
            onValueChange = {},
            readOnly = true,
            label = { Text("string atual")},
            modifier= Modifier.fillMaxWidth()
        )
        Button(onClick = { onAddWordClick(stringAtual)},
            modifier = Modifier.fillMaxWidth()) {
            Text("Adicionar palavra")
        }
        Button(onClick = { stringAtual = ""},
            modifier = Modifier.fillMaxWidth()) {
            Text("Reiniciar")
        }
    }

}
@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenPreview() {
    IntentsComposeTheme {
        Surface {
            HomeScreen(
                palavraRecebida = ""
            ) { }
        }
    }
}