package caterpillar.kvaka.grpc_proto;

import caterpillar.kvaka.entity.Invention;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.grpc.*;
import caterpillar.kvaka.service.impl.InventionServiceImpl;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@GRpcService
public class InventionServiceGrpcImpl extends InventionServiceGrpc.InventionServiceImplBase {

    private final InventionServiceImpl inventionService;

    @Autowired
    public InventionServiceGrpcImpl(InventionServiceImpl inventionService) {
        this.inventionService = inventionService;
    }

    @Override
    public void createInvention(CreateInventionRequest request, StreamObserver<CreateInventionResponse> responseObserver) {
        Invention inventionToCreate = new Invention(request.getInvention().getInvention());
        inventionToCreate.setStatus(Status.ACTIVE);
        inventionService.save(inventionToCreate);

        log.info("In [gRPC]createInvention created invention {}", inventionToCreate);
        responseObserver.onNext(CreateInventionResponse.newBuilder().setInvention(request.getInvention()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void readInvention(ReadInventionRequest request, StreamObserver<ReadInventionResponse> responseObserver) {
        Invention inventionToRead = inventionService.findInventionById(request.getInventionId());
        caterpillar.kvaka.grpc.Invention inv_grpc = caterpillar.kvaka.grpc.Invention.newBuilder()
                .setInventionId(inventionToRead.getInvention_id())
                .setInvention(inventionToRead.getInvention())
                .build();

        log.info("In [gRPC]readInvention found invention by id {}", inventionToRead.getInvention_id());
        responseObserver.onNext(ReadInventionResponse.newBuilder().setInvention(inv_grpc).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateInvention(UpdateInventionRequest request, StreamObserver<UpdateInventionResponse> responseObserver) {
        Invention inventionToUpdate = inventionService.findInventionById(request.getInvention().getInventionId());
        inventionToUpdate.setInvention(request.getInvention().getInvention());
        inventionService.save(inventionToUpdate);

        caterpillar.kvaka.grpc.Invention inv_grpc = caterpillar.kvaka.grpc.Invention.newBuilder()
                .setInventionId(inventionToUpdate.getInvention_id())
                .setInvention(inventionToUpdate.getInvention())
                .build();
        log.info("In [gRPC]updateInvention updated invention {}", inventionToUpdate);
        responseObserver.onNext(UpdateInventionResponse.newBuilder().setInvention(inv_grpc).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteInvention(DeleteInventionRequest request, StreamObserver<DeleteInventionResponse> responseObserver) {
        Invention inventionToDelete = inventionService.findInventionById(request.getInventionId());
        inventionToDelete.setStatus(Status.NOT_ACTIVE);
        inventionService.save(inventionToDelete);

        log.info("In [gRPC]deleteInvention invention with id {} was marked as deleted", inventionToDelete.getInvention_id());
        responseObserver.onNext(DeleteInventionResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllInventions(GetAllInventionsRequest request, StreamObserver<GetAllInventionsResponse> responseObserver) {
        List<Invention> inventionList = inventionService.findAllInventions();

        List<caterpillar.kvaka.grpc.Invention> allInventions = new ArrayList<>();

        for (Invention inv : inventionList) {
            caterpillar.kvaka.grpc.Invention tmp = caterpillar.kvaka.grpc.Invention.newBuilder()
                    .setInventionId(inv.getInvention_id())
                    .setInvention(inv.getInvention())
                    .build();
            allInventions.add(tmp);
        }

        log.info("In [gRPC]getAllInventions found {} records", allInventions.size());
        responseObserver.onNext(GetAllInventionsResponse.newBuilder().addAllInvention(allInventions).build());
        responseObserver.onCompleted();
    }
}