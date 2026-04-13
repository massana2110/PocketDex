package com.massana2110.pokeapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponseDTO(
    val id: Int?,
    val name: String?,
    val height: Int?,
    val weight: Int?,
    @SerialName("base_experience") val baseExperience: Int?,
    val sprites: SpritesDTO?,
    val types: List<TypeSlotDTO>?,
    val stats: List<StatSlotDTO>?,
    val abilities: List<AbilitySlotDTO>?
)

@Serializable
data class SpritesDTO(
    val other: OtherSpritesDTO
)

@Serializable
data class OtherSpritesDTO(
    val home: HomeSpriteDTO
)

@Serializable
data class HomeSpriteDTO(
    @SerialName("front_default") val frontDefault: String
)

@Serializable
data class TypeSlotDTO(
    val type: NamedApiResourceDTO
)

@Serializable
data class StatSlotDTO(
    @SerialName("base_stat") val baseStat: Int,
    val stat: NamedApiResourceDTO
)

@Serializable
data class AbilitySlotDTO(
    val ability: NamedApiResourceDTO,
    @SerialName("is_hidden") val isHidden: Boolean
)
