package ai.gobots.zsheridan.frontendapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FrontEndApplication

fun main(args: Array<String>) {
	runApplication<FrontEndApplication>(*args)
}
