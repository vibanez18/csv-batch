package com.example.batch.infrastructure.batch.writer

import com.example.batch.domain.ModelB2b
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class Writer(dataSource: DataSource): Logging, JdbcBatchItemWriter<ModelB2b>() {
    init {
        logger.info("${Thread.currentThread().name}:: Writing in database")

        setItemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
        setSql("INSERT INTO product_quality_b2b (model_id, audience, platform, content) VALUES (:modelId, :audience.name, :platform.name, null) " +
                "ON CONFLICT ON CONSTRAINT \"product_quality_b2bPK\" DO NOTHING" )
        setDataSource(dataSource)
        setAssertUpdates(false)
    }
}
