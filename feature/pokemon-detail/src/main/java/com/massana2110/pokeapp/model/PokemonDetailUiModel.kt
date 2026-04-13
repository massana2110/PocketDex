package com.massana2110.pokeapp.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.massana2110.pokeapp.feature.pokemondetail.R
import com.massana2110.pokeapp.ui.theme.bug
import com.massana2110.pokeapp.ui.theme.dark
import com.massana2110.pokeapp.ui.theme.dragon
import com.massana2110.pokeapp.ui.theme.electric
import com.massana2110.pokeapp.ui.theme.fairy
import com.massana2110.pokeapp.ui.theme.fighting
import com.massana2110.pokeapp.ui.theme.fire
import com.massana2110.pokeapp.ui.theme.flying
import com.massana2110.pokeapp.ui.theme.ghost
import com.massana2110.pokeapp.ui.theme.grass
import com.massana2110.pokeapp.ui.theme.ground
import com.massana2110.pokeapp.ui.theme.ice
import com.massana2110.pokeapp.ui.theme.normal
import com.massana2110.pokeapp.ui.theme.poison
import com.massana2110.pokeapp.ui.theme.psychic
import com.massana2110.pokeapp.ui.theme.rock
import com.massana2110.pokeapp.ui.theme.steel
import com.massana2110.pokeapp.ui.theme.water

data class PokemonDetailUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: String,
    val weight: String,
    val baseExperience: Int?,
    val description: String,
    val types: List<PokemonTypeUiModel>,
    val stats: List<PokemonStatUiModel>,
    val abilities: List<String>
)

data class PokemonStatUiModel(
    val name: String,
    val value: Int
)

enum class PokemonTypeUiModel(
    val code: String,
    val displayName: String,
    val color: Color,
    @param:DrawableRes val icon: Int
) {
    BUG(
        code = "bug",
        displayName = "Bicho",
        color = bug,
        icon = R.drawable.ic_type_bug
    ),
    DARK(
        code = "dark",
        displayName = "Siniestro",
        color = dark,
        icon = R.drawable.ic_type_dark
    ),
    DRAGON(
        code = "dragon",
        displayName = "Dragón",
        color = dragon,
        icon = R.drawable.ic_type_dragon
    ),
    ELECTRIC(
        code = "electric",
        displayName = "Eléctrico",
        color = electric,
        icon = R.drawable.ic_type_electric
    ),
    FAIRY(
        code = "fairy",
        displayName = "Hada",
        color = fairy,
        icon = R.drawable.ic_type_fairy
    ),
    FIGHTING(
        code = "fighting",
        displayName = "Lucha",
        color = fighting,
        icon = R.drawable.ic_type_fighting
    ),
    FIRE(
        code = "fire",
        displayName = "Fuego",
        color = fire,
        icon = R.drawable.ic_type_fire
    ),
    FLYING(
        code = "flying",
        displayName = "Volador",
        color = flying,
        icon = R.drawable.ic_type_flying
    ),
    GHOST(
        code = "ghost",
        displayName = "Fantasma",
        color = ghost,
        icon = R.drawable.ic_type_ghost
    ),
    GRASS(
        code = "grass",
        displayName = "Hierba",
        color = grass,
        icon = R.drawable.ic_type_grass
    ),
    GROUND(
        code = "ground",
        displayName = "Tierra",
        color = ground,
        icon = R.drawable.ic_type_ground
    ),
    ICE(
        code = "ice",
        displayName = "Hielo",
        color = ice,
        icon = R.drawable.ic_type_ice
    ),
    NORMAL(
        code = "normal",
        displayName = "Normal",
        color = normal,
        icon = R.drawable.ic_type_normal
    ),
    POISON(
        code = "poison",
        displayName = "Veneno",
        color = poison,
        icon = R.drawable.ic_type_poison
    ),
    PSYCHIC(
        code = "psychic",
        displayName = "Psíquico",
        color = psychic,
        icon = R.drawable.ic_type_psychic
    ),
    ROCK(
        code = "rock",
        displayName = "Roca",
        color = rock,
        icon = R.drawable.ic_type_rock
    ),
    STEEL(
        code = "steel",
        displayName = "Acero",
        color = steel,
        icon = R.drawable.ic_type_steel
    ),
    WATER(
        code = "water",
        displayName = "Agua",
        color = water,
        icon = R.drawable.ic_type_water
    );

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code } ?: NORMAL
    }
}