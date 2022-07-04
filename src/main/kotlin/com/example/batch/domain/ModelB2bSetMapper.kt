package com.example.batch.domain

import org.springframework.batch.item.file.mapping.FieldSetMapper
import org.springframework.batch.item.file.transform.FieldSet

class ModelB2bSetMapper: FieldSetMapper<ModelB2b> {
    override fun mapFieldSet(fieldSet: FieldSet): ModelB2b {
        return ModelB2b(
//            mmId = fieldSet.readString("mmid")!!.ifBlank { null }?.toLong(),
            modelId = fieldSet.readLong("model_id"),
            market = Market.B2B,
            platform = fieldSet.readString("country").uppercase().let(Platform::valueOf),
            audience = fieldSet.readString("audience").ifBlank { null }?.uppercase()?.let(Audience::valueOf) ?: Audience.CORPO,
//            segment = fieldSet.readString("segment").ifBlank { null }?.uppercase()?.let(SegmentB2b::valueOf) ?: SegmentB2b.Empty,
//            competitiveness = ModelCompetitiveness(
//                type = parseCompetitiveness(fieldSet.readString("competitiveness")!!),
//                range = fieldSet.readString("competitiveness_range")!!.ifBlank { null }?.toInt(),
//                isDeliveryTimeCompetitive = fieldSet.readString("delivery_time_competitive")!!.ifBlank { null }?.toBoolean() ?: false,
//            ),
//            quality = ModelQuality(
//                hasManufacturerPdf = fieldSet.readString("has_manufacturer_pdf")!!.ifBlank { null }?.toBoolean() ?: false,
//                has3FirstAttributesCompleted = fieldSet.readString("has_3_first_attributes_completed")!!.ifBlank { null }?.toBoolean() ?: false,
//                images = fieldSet.readString("nb_images")!!.ifBlank { null }?.toLong() ?: 0,
//                score = 0
//            )

            mmId = null,
            segment = SegmentB2b.Empty,
            competitiveness = ModelCompetitiveness(type = Competitiveness.NOT_AVAILABLE, 0, false),
            quality = ModelQuality(false, false, 0)
        )
    }

    private fun parseCompetitiveness(text: String) =
        text.ifBlank { null }
            ?.uppercase()
            ?.replace(" ", "_")
            ?.replace("DISCONECTED", "DISCONNECTED")
            ?.let(Competitiveness::valueOf)
            ?: Competitiveness.NOT_AVAILABLE
}