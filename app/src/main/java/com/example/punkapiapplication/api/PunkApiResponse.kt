package com.example.punkapiapplication.api

import com.example.punkapiapplication.data.PunkData

data class PunkApiResponse(
    val results: List<PunkData>
)