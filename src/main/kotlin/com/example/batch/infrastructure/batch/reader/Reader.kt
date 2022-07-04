package com.example.batch.infrastructure.batch.reader

import com.example.batch.domain.b2b.model.ModelB2b
import com.example.batch.domain.b2b.ModelB2bSetMapper
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils

@Component
@StepScope
class Reader: FlatFileItemReader<ModelB2b>() {
    init {
        val tokenizer = DelimitedLineTokenizer(";")
        tokenizer.setStrict(false)
        tokenizer.setNames(
            "mmid",
            "model_id",
            "country",
            "audience",
//            "segment",
//            "competitiveness",
//            "competitiveness_range",
//            "delivery_time_competitive",
//            "has_manufacturer_pdf",
//            "has_3_first_attributes_completed",
//            "nb_images"
        )
        setLinesToSkip(1)
        val modelB2bDefaultMapper = DefaultLineMapper<ModelB2b>()
        modelB2bDefaultMapper.setLineTokenizer(tokenizer)
        modelB2bDefaultMapper.setFieldSetMapper(ModelB2bSetMapper())
        modelB2bDefaultMapper.afterPropertiesSet()
//        setResource(FileSystemResource(getSmallFile()))
        setResource(FileSystemResource(getSmallFile()))
        setLineMapper(modelB2bDefaultMapper)
    }

    private fun getSmallFile() =
        ResourceUtils.getFile("classpath:small_file.csv")

//    private fun getHugeFile() =
//        ResourceUtils.getFile("classpath:huge_file.csv")
}