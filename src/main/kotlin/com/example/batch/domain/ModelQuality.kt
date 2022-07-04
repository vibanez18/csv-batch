package com.example.batch.domain

data class ModelQuality(
    val hasManufacturerPdf: Boolean,
    val has3FirstAttributesCompleted: Boolean,
    val score: Int,
    val images: Long = 0
)
