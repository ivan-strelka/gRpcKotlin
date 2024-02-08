package t.service

import io.grpc.examples.animals.BarkReply
import io.grpc.examples.animals.BarkRequest
import io.grpc.examples.animals.DogGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
@Primary
class DogsClientService {
    @GrpcClient("pet")
    private lateinit var dogStub: DogGrpc.DogBlockingStub

    fun barkRequest(request: BarkRequest): BarkReply {
        return dogStub.bark(request)
    }
}