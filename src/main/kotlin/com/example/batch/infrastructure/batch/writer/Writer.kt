package com.example.batch.infrastructure.batch.writer

import com.example.batch.domain.b2b.entity.B2bEntity
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class Writer(dataSource: DataSource): Logging, JdbcBatchItemWriter<B2bEntity>() {
    init {
        this.setItemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider())
        this.setSql("INSERT INTO product_quality_b2b (model_id, audience, platform, content) VALUES (:modelId, :audience, :platform, :content::jsonb) " +
                "ON CONFLICT ON CONSTRAINT \"product_quality_b2bPK\" DO NOTHING" )
        this.setDataSource(dataSource)
        this.setAssertUpdates(false)
    }
}
