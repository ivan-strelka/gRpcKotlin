package t

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DogsTestApp(private val dogSteps: DogSteps) : CommandLineRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<DogsTestApp>(*args)
        }
    }

    override fun run(vararg args: String?) {
        try {
            dogSteps.makeBark()
        } catch (e: Exception) {
            println("Не гавкает, потому что: " + e.message.toString())
        }
    }
}