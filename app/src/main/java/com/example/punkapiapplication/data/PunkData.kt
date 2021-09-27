package com.example.punkapiapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PunkData(
    val id: Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    val abv: Double,
    val ibu: Double,
    val target_fg: Double,
    val target_og: Double,
    val ebc: Double,
    val srm: Double,
    val ph: Double,
    val attenuation_level: Double,
//    val volume: Volume,
//    val boil_volume: BoilVolume,
//    val method: Method,
//    val ingredients: Ingredients,
    val food_pairing: List<String>,
    val brewers_tips: String,
    val contributed_by: String,
    var checked: Boolean = false
) : Parcelable
