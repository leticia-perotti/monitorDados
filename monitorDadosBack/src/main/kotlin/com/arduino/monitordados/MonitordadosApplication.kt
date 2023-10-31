package com.arduino.monitordados

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.socket.config.annotation.EnableWebSocket

@EnableAsync
@EnableScheduling
@EnableWebSocket
@SpringBootApplication
class MonitordadosApplication

fun main(args: Array<String>) {
	runApplication<MonitordadosApplication>(*args)
}
