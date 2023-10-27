package com.arduino.monitordados

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication
class MonitordadosApplication

fun main(args: Array<String>) {
	runApplication<MonitordadosApplication>(*args)
}
