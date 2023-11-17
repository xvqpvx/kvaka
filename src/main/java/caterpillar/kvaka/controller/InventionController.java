package caterpillar.kvaka.controller;

import caterpillar.kvaka.entity.Invention;
import caterpillar.kvaka.entity.Status;
import caterpillar.kvaka.service.impl.InventionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "InventionController", description = "API that allows perform CRUD-operations with Inventions")
@RestController
@RequestMapping("/api/inventions")
public class InventionController {

    private final InventionServiceImpl inventionService;

    @Autowired
    public InventionController(InventionServiceImpl inventionService) {
        this.inventionService = inventionService;
    }


    @Operation(
            summary = "Get all inventions",
            description = "Allows us to get all inventions"
    )
    @GetMapping
    public ResponseEntity<List<Invention>> getAllInventions() {
        return ResponseEntity.ok(inventionService.findAllInventions());
    }

    @Operation(
            summary = "Get invention",
            description = "Allows us to get invention by id"
    )
    @GetMapping("/{invention_id}")
    public ResponseEntity<Invention> getInventionById(
            @PathVariable @Parameter(description = "Invention identifier") int invention_id) {

        Invention invention = inventionService.findInventionById(invention_id);
        if (invention == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invention);
    }


    @Operation(
            summary = "Add invention",
            description = "Allows us to add invention"
    )
    @PostMapping
    public ResponseEntity<Invention> addInvention(
            @RequestBody @Parameter(description = "Invention entity") Invention invention) {
        invention.setStatus(Status.ACTIVE);
        inventionService.save(invention);
        return ResponseEntity.ok(invention);
    }


    @Operation(
            summary = "Edit invention",
            description = "Allows us to edit invention by id"
    )
    @PutMapping("/edit/{invention_id}")
    public ResponseEntity<Invention> updateInvention(
            @RequestBody @Parameter(description = "Changed invention") Invention invention,
            @PathVariable @Parameter(description = "Invention id") int invention_id) {
        Invention existingInvention = inventionService.findInventionById(invention_id);
        if (existingInvention == null) {
            return ResponseEntity.notFound().build();
        }
        existingInvention.setInvention(invention.getInvention());
        inventionService.save(existingInvention);
        return ResponseEntity.ok(existingInvention);
    }


    @Operation(
            summary = "Delete invention",
            description = "Allows us to delete invention by id"
    )
    @DeleteMapping("/delete/{invention_id}")
    public ResponseEntity<Invention> deleteInvention(
            @PathVariable @Parameter(description = "Invention id") int invention_id) {
        Invention existingInvention = inventionService.findInventionById(invention_id);
        if (existingInvention == null) {
            return ResponseEntity.notFound().build();
        }

        inventionService.deleteInventionById(invention_id);
        return ResponseEntity.ok().build();
    }
}