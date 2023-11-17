package caterpillar.kvaka.grpc_proto;

import caterpillar.kvaka.service.impl.InventionServiceImpl;
import caterpillar.kvaka.service.impl.InventorServiceImpl;
import caterpillar.kvaka.service.impl.InventorsInventionsServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcServerConfiguration {

    @Value("${grpc.port}")
    private int grpcPort;

    @Bean
    public Server grpcServer(InventionServiceImpl inventionService,
                             InventorServiceImpl inventorService,
                             InventorsInventionsServiceImpl recordsService) throws IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(grpcPort)
                .addService(new InventionServiceGrpcImpl(inventionService))
                .addService(new InventorServiceGrpcImpl(inventorService))
                .addService(new LinkedTableServiceGrpcImpl(recordsService));
        Server server = serverBuilder.build();
        server.start();
        return server;
    }
}