package com.massana2110.pokeapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.massana2110.pokeapp.feature.pokemondetail.R
import com.massana2110.pokeapp.model.PokemonDetailUiModel
import com.massana2110.pokeapp.model.PokemonStatUiModel
import com.massana2110.pokeapp.model.PokemonTypeUiModel
import com.massana2110.pokeapp.ui.components.badge.PocketDexBadge
import com.massana2110.pokeapp.ui.components.container.PocketDexScreenContainer
import com.massana2110.pokeapp.ui.components.image.PocketDexRemoteImage
import com.massana2110.pokeapp.ui.components.loaders.CircularLoader
import com.massana2110.pokeapp.ui.components.progress.PocketDexHorizontalProgressBar
import com.massana2110.pokeapp.ui.components.spacer.Spacer16
import com.massana2110.pokeapp.ui.components.spacer.Spacer1f
import com.massana2110.pokeapp.ui.components.spacer.Spacer4
import com.massana2110.pokeapp.ui.components.spacer.Spacer8
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.gray40
import com.massana2110.pokeapp.ui.theme.grayAA
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PokemonDetailScreen(
    pokemonId: Int,
    viewModel: PokemonDetailViewModel = koinViewModel(
        parameters = { parametersOf(pokemonId) }
    ),
    onNavigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PokemonDetailScreenContent(
        uiState = uiState,
        onNavigateBack = onNavigateBack
    )
}

@Composable
private fun PokemonDetailScreenContent(
    uiState: PokemonDetailUiState,
    onNavigateBack: () -> Unit = {}
) {
    val pokemon = uiState.pokemon

    PocketDexScreenContainer(
        title = pokemon?.name,
        actionText = "#%03d".format(pokemon?.id),
        showBackButton = true,
        onTopBarBackClick = onNavigateBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when {
                uiState.isLoading -> {
                    CircularLoader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = uiState.errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
                    PokemonDetailHeader(
                        pokemonSprite = pokemon?.imageUrl.orEmpty(),
                        pokemonTypes = pokemon?.types.orEmpty(),
                        pokemonWeight = pokemon?.weight.orEmpty(),
                        pokemonHeight = pokemon?.height.orEmpty()
                    )
                    Spacer1f()
                    Text(
                        text = pokemon?.description.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = grayAA
                    )
                    Spacer1f()
                    PokemonStats(
                        stats = pokemon?.stats.orEmpty(),
                        statColor = pokemon?.types?.firstOrNull()?.color
                            ?: MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.PokemonDetailHeader(
    pokemonSprite: String,
    pokemonTypes: List<PokemonTypeUiModel>,
    pokemonWeight: String,
    pokemonHeight: String
) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(MaterialTheme.shapes.large)
                .background(pokemonTypes.first().color.copy(alpha = 0.6f))
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PocketDexRemoteImage(
                modifier = Modifier.height(200.dp),
                imageUrl = pokemonSprite,
                scale = ContentScale.FillHeight
            )
            Spacer4()
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pokemonTypes.forEach { type ->
                    PocketDexBadge(
                        badgeColor = type.color,
                        icon = type.icon,
                        label = type.displayName
                    )
                }
            }
            Spacer16()
        }
    }
    Spacer16()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokemonMeasurementItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.pokemon_detail_weight_label),
            value = pokemonWeight,
            icon = R.drawable.ic_weight_scale
        )
        VerticalDivider(modifier = Modifier.padding(vertical = 8.dp))
        PokemonMeasurementItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.pokemon_detail_height_label),
            value = pokemonHeight,
            icon = R.drawable.ic_ruler
        )
    }
}

@Composable
private fun PokemonMeasurementItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(icon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = title
        )
        Spacer8()
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun ColumnScope.PokemonStats(
    stats: List<PokemonStatUiModel>,
    statColor: androidx.compose.ui.graphics.Color
) {
    Text(
        text = stringResource(R.string.pokemon_detail_stats_title),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer16()
    stats.forEach { stat ->
        PokemonStatItem(stat = stat, statColor = statColor)
        Spacer16()
    }
}

@Composable
private fun PokemonStatItem(
    stat: PokemonStatUiModel,
    statColor: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.weight(0.4f),
            text = stringResource(stat.name.toStatAbbreviation()),
            style = MaterialTheme.typography.bodyMedium,
            color = gray40
        )
        PocketDexHorizontalProgressBar(
            modifier = Modifier.weight(0.6f),
            progress = stat.value / STAT_MAX_VALUE.toFloat(),
            trackColor = statColor.copy(alpha = 0.2f),
            indicatorColor = statColor
        )
        Text(
            text = "%03d".format(stat.value),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun String.toStatAbbreviation(): Int = when (this) {
    "hp" -> R.string.pokemon_detail_hp_label
    "attack" -> R.string.pokemon_detail_attack_label
    "defense" -> R.string.pokemon_detail_defense_label
    "special-attack" -> R.string.pokemon_detail_sp_attack_label
    "special-defense" -> R.string.pokemon_detail_sp_defense_label
    "speed" -> R.string.pokemon_detail_speed_label
    else -> 0
}

@Preview
@Composable
private fun PokemonDetailScreenPreview() {
    PocketDexTheme {
        PokemonDetailScreenContent(
            uiState = PokemonDetailUiState(
                pokemon = PokemonDetailUiModel(
                    id = 1,
                    name = "Bulbasaur",
                    imageUrl = "",
                    height = "0.7 m",
                    weight = "6.9 kg",
                    baseExperience = 64,
                    description = "Tiene una extraña semilla plantada en su lomo al nacer. La semilla crece lentamente absorbiendo la energía del cuerpo como nutriente.",
                    types = listOf(
                        PokemonTypeUiModel.GRASS,
                        PokemonTypeUiModel.POISON
                    ),
                    stats = listOf(
                        PokemonStatUiModel("hp", 45),
                        PokemonStatUiModel("attack", 49),
                        PokemonStatUiModel("defense", 49),
                        PokemonStatUiModel("special-attack", 65),
                        PokemonStatUiModel("special-defense", 65),
                        PokemonStatUiModel("speed", 45)
                    ),
                    abilities = emptyList()
                )
            )
        ) { }
    }
}

private const val STAT_MAX_VALUE = 175
