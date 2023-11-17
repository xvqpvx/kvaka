package caterpillar.kvaka;

import caterpillar.kvaka.grpc_proto.InventionServiceGrpcImpl;
import caterpillar.kvaka.service.impl.InventionServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LabApplication {

//    @Autowired
//    static InventionServiceImpl inventionService;

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(LabApplication.class, args);



//        Server server = ServerBuilder.forPort(9090)
//                .addService(new InventionServiceGrpcImpl(inventionService))
//                .build();
//
//        server.start();
//
//        server.awaitTermination();
    }


}
