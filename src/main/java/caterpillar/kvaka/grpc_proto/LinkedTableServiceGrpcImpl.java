package caterpillar.kvaka.grpc_proto;

import caterpillar.kvaka.entity.InventorsInventions;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.grpc.*;
import caterpillar.kvaka.service.impl.InventorsInventionsServiceImpl;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GRpcService
public class LinkedTableServiceGrpcImpl extends LinkTableServiceGrpc.LinkTableServiceImplBase {

    private final InventorsInventionsServiceImpl recordsService;

    @Autowired
    public LinkedTableServiceGrpcImpl(InventorsInventionsServiceImpl recordsService) {
        this.recordsService = recordsService;
    }

    @Override
    public void createLink(CreateLinkRecordRequest request, StreamObserver<CreateLinkRecordResponse> responseObserver) {
        InventorsInventions recordToCreate = new InventorsInventions(request.getLink().getRecordId(),
                request.getLink().getInventorId(),
                request.getLink().getInventionId());

        recordsService.save(recordToCreate);

        log.info("In [gRPC]createLink created link {}", recordToCreate);
        responseObserver.onNext(CreateLinkRecordResponse.newBuilder().setLink(request.getLink()).build());
        responseObserver.onCompleted();
    }

    @Override
    public void readLink(ReadLinkRecordRequest request, StreamObserver<ReadLinkRecordResponse> responseObserver) {
        InventorsInventions recordToRead = recordsService.findRecordById(request.getRecordId());
        caterpillar.kvaka.grpc.LinkTable linkTable = LinkTable.newBuilder()
                .setRecordId(recordToRead.getId_record())
                .setInventorId(recordToRead.getId_inventor())
                .setInventionId(recordToRead.getId_invention())
                .build();

        log.info("[gRPC]readLink found linked record by id {}", recordToRead.getId_record());
        responseObserver.onNext(ReadLinkRecordResponse.newBuilder().setLinkTable(linkTable).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateLink(UpdateLinkRecordRequest request, StreamObserver<UpdateLinkRecordResponse> responseObserver) {
        InventorsInventions recordToUpdate = recordsService.findRecordById(request.getLink().getRecordId());
        recordToUpdate.setId_inventor(request.getLink().getInventorId());
        recordToUpdate.setId_invention(request.getLink().getInventionId());
        recordsService.save(recordToUpdate);

        caterpillar.kvaka.grpc.LinkTable linkTable = LinkTable.newBuilder()
                .setRecordId(recordToUpdate.getId_record())
                .setInventorId(recordToUpdate.getId_inventor())
                .setInventionId(recordToUpdate.getId_invention())
                .build();

        log.info("In [gRPC]updateLink updated record {}", recordToUpdate);
        responseObserver.onNext(UpdateLinkRecordResponse.newBuilder().setLink(linkTable).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteLink(DeleteLinkRecordRequest request, StreamObserver<DeleteLinkRecordResponse> responseObserver) {
        InventorsInventions recordToDelete = recordsService.findRecordById(request.getRecordId());
        recordToDelete.setStatus(Status.NOT_ACTIVE);
        recordsService.save(recordToDelete);

        log.info("In [gRPC]deleteLink record with id {} was marked as deleted", recordToDelete.getId_record());
        responseObserver.onNext(DeleteLinkRecordResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllRecords(GetAllLinkedRecordsRequest request, StreamObserver<GetAllLinkedRecordsResponse> responseObserver) {

    }
}
