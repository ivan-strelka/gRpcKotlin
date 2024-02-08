package t

import io.grpc.examples.animals.BarkRequest
import org.springframework.stereotype.Component
import t.service.DogsClientService

@Component
class DogSteps(private val stepDogGrpcService: DogsClientService) {
    fun makeBark() {
        val request = BarkRequest.newBuilder().build()
        val response = stepDogGrpcService.barkRequest(request)
    }
}