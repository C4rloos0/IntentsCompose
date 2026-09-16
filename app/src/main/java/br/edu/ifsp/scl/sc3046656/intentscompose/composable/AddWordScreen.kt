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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifsp.scl.sc3046656.intentscompose.ui.theme.IntentsComposeTheme

@Composable
fun AddWordScreen(
    stringRecebida: String,
    modifier : Modifier = Modifier,
    onConcatenarClick: (String) -> Unit
){
    var palavraNova by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxWidth()){
        OutlinedTextField(
            value = stringRecebida,
            onValueChange = {},
            readOnly = true,
            label = {Text("String atual")},
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = palavraNova,
            label = { Text("Nova palavra")},
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {palavraNova = it}
        )
        Button(
            onClick = { onConcatenarClick(palavraNova) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Concatenar")
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
fun AddWordScreenPreview() {
    IntentsComposeTheme{
        Surface {
            AddWordScreen(
                stringRecebida = "Olá mundo"
            ) { }
        }
    }
}