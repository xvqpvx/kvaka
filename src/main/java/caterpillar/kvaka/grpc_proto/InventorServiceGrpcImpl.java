package caterpillar.kvaka.grpc_proto;

import caterpillar.kvaka.entity.Inventor;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.grpc.*;
import caterpillar.kvaka.service.impl.InventorServiceImpl;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@GRpcService
public class InventorServiceGrpcImpl extends InventorServiceGrpc.InventorServiceImplBase {

    private final InventorServiceImpl inventorService;

    @Autowired
    public InventorServiceGrpcImpl(InventorServiceImpl inventorService) {
        this.inventorService = inventorService;
    }

    @Override
    public void createInventor(CreateInventorRequest request, StreamObserver<CreateInventorResponse> responseObserver) {
        Inventor inventorToCreate = new Inventor(request.getInventor().getLastname(), request.getInventor().getFirstname());
        inventorToCreate.setStatus(Status.ACTIVE);
        inventorService.save(inventorToCreate);

        log.info("In [gRPC]createInventor created inventor {}", inventorToCreate);
        responseObserver.onNext(CreateInventorResponse.newBuilder().setInventor(request.getInventor()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void readInventor(ReadInventorRequest request, StreamObserver<ReadInventorResponse> responseObserver) {
        Inventor inventorToRead = inventorService.findInventorById(request.getInventorId());
        caterpillar.kvaka.grpc.Inventor inv_grpc = caterpillar.kvaka.grpc.Inventor.newBuilder()
                .setInventorId(inventorToRead.getInventor_id())
                .setLastname(inventorToRead.getLastname())
                .setFirstname(inventorToRead.getFirstname())
                .build();

        log.info("In [gRPC]readInventor found invention by id {}", inventorToRead.getInventor_id());
        responseObserver.onNext(ReadInventorResponse.newBuilder().setInventor(inv_grpc).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateInventor(UpdateInventorRequest request, StreamObserver<UpdateInventorResponse> responseObserver) {
        Inventor inventorToUpdate = inventorService.findInventorById(request.getInventor().getInventorId());
        inventorToUpdate.setLastname(request.getInventor().getLastname());
        inventorToUpdate.setFirstname(request.getInventor().getFirstname());
        inventorService.save(inventorToUpdate);

        caterpillar.kvaka.grpc.Inventor inv_grpc = caterpillar.kvaka.grpc.Inventor.newBuilder()
                .setInventorId(inventorToUpdate.getInventor_id())
                .setLastname(inventorToUpdate.getLastname())
                .setFirstname(inventorToUpdate.getFirstname())
                .build();
        log.info("In [gRPC]updateInventor updated inventor {}", inventorToUpdate);
        responseObserver.onNext(UpdateInventorResponse.newBuilder().setInventor(inv_grpc).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteInventor(DeleteInventorRequest request, StreamObserver<DeleteInventorResponse> responseObserver) {
        Inventor inventorToDelete = inventorService.findInventorById(request.getInventorsId());
        inventorToDelete.setStatus(Status.NOT_ACTIVE);
        inventorService.save(inventorToDelete);

        log.info("In [gRPC]deleteInventor inventor with id {} was marked as deleted", inventorToDelete.getInventor_id());
        responseObserver.onNext(DeleteInventorResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllInventors(GetAllInventorsRequest request, StreamObserver<GetAllInventorsResponse> responseObserver) {
        List<Inventor> inventorList = inventorService.findAllInventors();
        List<caterpillar.kvaka.grpc.Inventor> allInventors = new ArrayList<>();

        for (Inventor inv : inventorList){
            caterpillar.kvaka.grpc.Inventor tmp = caterpillar.kvaka.grpc.Inventor.newBuilder()
                    .setInventorId(inv.getInventor_id())
                    .setLastname(inv.getLastname())
                    .setFirstname(inv.getFirstname())
                    .build();
            allInventors.add(tmp);
        }

        log.info("In [gRPC]getAllInventors found {} records", allInventors.size());
        responseObserver.onNext(GetAllInventorsResponse.newBuilder().addAllInventor(allInventors).build());
        responseObserver.onCompleted();
    }
}
