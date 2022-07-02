package com.example.batch.infrastructure.batch

import com.example.batch.infrastructure.batch.processor.Processor
import com.example.batch.infrastructure.batch.reader.Reader
import com.example.batch.infrastructure.batch.writer.Writer
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

@Configuration
@EnableBatchProcessing
class JobConfiguration(val jobBuilderFactory: JobBuilderFactory,
                       val stepBuilderFactory: StepBuilderFactory,
                       @Value("\${ms-batch.size}") val chunkSize: Int
): Logging {
    @Bean
    @StepScope
    fun reader(): ListItemReader<String> = Reader()()

    @Bean
    fun itemProcessor(): ItemProcessor<String, String> = Processor()()

    @Bean
    fun itemWriter() : ItemWriter<String> = Writer()()

    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val exec = ThreadPoolTaskExecutor()
        exec.corePoolSize = 5
        exec.maxPoolSize = 10
        exec.setThreadNamePrefix("poc-batch")
        return exec
    }

    @Bean
    fun insertInDatabaseStep(): Step {
        return stepBuilderFactory["insertInDatabaseStep"]
            .chunk<String, String>(10)
            .reader(reader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .faultTolerant()
            .taskExecutor(taskExecutor())
            .build()
    }

    @Bean
    fun updateCatalog(): Job {
        return jobBuilderFactory["updateCatalog"]
            .start(insertInDatabaseStep())
            .build()
    }
}