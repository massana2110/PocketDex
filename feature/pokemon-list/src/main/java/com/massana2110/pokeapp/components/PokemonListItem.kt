package com.massana2110.pokeapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.massana2110.pokeapp.ui.components.image.PocketDexRemoteImage
import com.massana2110.pokeapp.ui.components.spacer.Spacer4
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.grayAA

@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    pokemonId: Int,
    pokemonName: String,
    pokemonImageUrl: String,
    onClickPokemon: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        onClick = { onClickPokemon(pokemonId) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "#$pokemonId",
                style = MaterialTheme.typography.bodySmall,
                color = grayAA,
                textAlign = TextAlign.End
            )
            Spacer4()
            PocketDexRemoteImage(
                modifier = Modifier.width(80.dp).align(Alignment.CenterHorizontally),
                imageUrl = pokemonImageUrl
            )
            Spacer4()
            Text(
                text = pokemonName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun PokemonListItemPreview() {
    PocketDexTheme {
        PokemonListItem(
            pokemonId = 1,
            pokemonName = "Pikachu",
            pokemonImageUrl = "https://pokemon-imageurl",
            onClickPokemon = {}
        )
    }
}