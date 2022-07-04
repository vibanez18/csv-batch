package com.example.batch.domain

import com.example.batch.domain.Competitiveness

data class ModelCompetitiveness(
    val type: Competitiveness,
    val range: Int?,
    val isDeliveryTimeCompetitive: Boolean,
)
