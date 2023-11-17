package caterpillar.kvaka.controller;

import caterpillar.kvaka.dto.InventorsInventionsDto;
import caterpillar.kvaka.entity.InventorsInventions;
import caterpillar.kvaka.service.impl.InventorsInventionsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Inventor and Inventions Controller",
        description = "API that allows perform CRUD-operations with Inventors and belongs to them Inventions")
@RestController
@RequestMapping("/api/allRecords")
public class InventorInventionController {

    private final InventorsInventionsServiceImpl recordsService;

    @Autowired
    public InventorInventionController(InventorsInventionsServiceImpl recordsService) {
        this.recordsService = recordsService;
    }

    @Operation(
            summary = "Get related records",
            description = "Allows us to get all related records"
    )
    @GetMapping
    public ResponseEntity<List<InventorsInventionsDto>> getAllRecords() {
        return ResponseEntity.ok(recordsService.getInventorsAndInventions());
    }

    @Operation(
            summary = "Get related record",
            description = "Allows us to get related record by identifier"
    )
    @GetMapping("/{id_record}")
    public ResponseEntity<InventorsInventions> getRecordById(
            @PathVariable @Parameter(description = "Record identifier") int id_record) {
        InventorsInventions result = recordsService.findRecordById(id_record);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


    @Operation(
            summary = "Add related record",
            description = "Allows us to add related record"
    )
    @PostMapping
    public ResponseEntity<InventorsInventions> addRecord(
            @RequestBody @Parameter(description = "Record entity") InventorsInventions record) {
        recordsService.save(record);
        return ResponseEntity.ok(record);
    }


    @Operation(
            summary = "Edit record ",
            description = "Allows us to edit related record"
    )
    @PutMapping("/edit/{id_record}")
    public ResponseEntity<InventorsInventions> updateInvention(
            @RequestBody @Parameter(description = "Record entity") InventorsInventions record,
            @PathVariable @Parameter(description = "Record identifier") int id_record) {
        InventorsInventions existingRecord = recordsService.findRecordById(id_record);
        if (existingRecord == null) {
            return ResponseEntity.notFound().build();
        }
        existingRecord.setId_invention(record.getId_invention());
        existingRecord.setId_inventor(record.getId_inventor());
        recordsService.save(existingRecord);
        return ResponseEntity.ok(existingRecord);
    }


    @Operation(
            summary = "Delete related record",
            description = "Allows us to delete related record "
    )
    @DeleteMapping("/delete/{id_record}")
    public ResponseEntity<InventorsInventions> deleteRecord(
            @PathVariable @Parameter(description = "Record identifier") int id_record) {
        InventorsInventions record = recordsService.findRecordById(id_record);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        recordsService.deleteRecordById(id_record);
        return ResponseEntity.ok().build();
    }
}
