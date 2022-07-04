package com.example.batch

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component


@SpringBootApplication
class BatchApplication

fun main(args: Array<String>) {
	runApplication<BatchApplication>(*args)
}

@Component
class Job2CommandLineRunner : CommandLineRunner, Logging {
	@Autowired
	private lateinit var jobLauncher: JobLauncher

	@Autowired
	private lateinit var updateCatalog: Job

	@Throws(Exception::class)
	override fun run(vararg args: String) {
		val jobParameters = JobParametersBuilder()
			.addLong("time", System.currentTimeMillis())
			.toJobParameters()
		val launcher = jobLauncher.run(updateCatalog, jobParameters)

		logger.info("JOB STATUS: ${launcher.status}")
	}
}
