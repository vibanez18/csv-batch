package com.example.batch

import org.apache.logging.log4j.kotlin.Logging
import org.junit.jupiter.api.Test
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BatchApplicationTests: Logging {

	@Autowired
	lateinit var updateCatalog: Job

	@Autowired
	lateinit var jobLauncher: JobLauncher

	@Test
	fun contextLoads() {
	}

	@Test
	fun `Launch Job`() {
		val jobParameters = JobParametersBuilder()
			.addLong("time", System.currentTimeMillis())
			.toJobParameters()
		val launcher = jobLauncher.run(updateCatalog, jobParameters)

		logger.info("Start Time ${launcher.startTime}")
		logger.info("End Time ${launcher.endTime}")
	}

}
