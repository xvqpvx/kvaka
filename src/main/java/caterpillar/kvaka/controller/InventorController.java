package caterpillar.kvaka.controller;

import caterpillar.kvaka.entity.Inventor;
import caterpillar.kvaka.service.impl.InventorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "InventorController", description = "API that allows perform CRUD-operations with Inventors")
@RestController
@RequestMapping("/api/inventors")
public class InventorController {

    private final InventorServiceImpl inventorService;

    @Autowired
    public InventorController(InventorServiceImpl inventorService) {
        this.inventorService = inventorService;
    }


    @Operation(
            summary = "Get all inventors",
            description = "Allows us to get all inventors"
    )
    @GetMapping
    public ResponseEntity<List<Inventor>> getAllInventors() {
        return ResponseEntity.ok(inventorService.findAllInventors());
    }


    @Operation(
            summary = "Get inventor",
            description = "Allows us to get inventor by identifier"
    )
    @GetMapping("/{inventor_id}")
    public ResponseEntity<Inventor> getInventorById(
            @PathVariable @Parameter(description = "Inventor identifier") int inventor_id) {
        Inventor inventor = inventorService.findInventorById(inventor_id);
        if (inventor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inventor);
    }


    @Operation(
            summary = "Add inventor",
            description = "Allows us to add inventor"
    )
    @PostMapping
    public ResponseEntity<Inventor> addInventor(
            @RequestBody @Parameter(description = "Inventor entity") Inventor inventor) {
        inventorService.save(inventor);
        return ResponseEntity.ok(inventor);
    }


    @Operation(
            summary = "Edit inventor",
            description = "Allows us to edit inventor by identifier"
    )
    @PutMapping("/edit/{inventor_id}")
    public ResponseEntity<Inventor> updateInventor(
            @RequestBody @Parameter(description = "Inventor entity") Inventor inventor,
            @PathVariable @Parameter(description = "Inventor identifier") int inventor_id) {
        Inventor existingInventor = inventorService.findInventorById(inventor_id);
        if (existingInventor == null) {
            return ResponseEntity.notFound().build();
        }
        existingInventor.setFirstname(inventor.getFirstname());
        existingInventor.setLastname(inventor.getLastname());
        inventorService.save(existingInventor);
        return ResponseEntity.ok(existingInventor);
    }


    @Operation(
            summary = "Delete inventor",
            description = "Allows us to delete inventor by identifier"
    )
    @DeleteMapping("/delete/{inventor_id}")
    public ResponseEntity<Inventor> deleteInventor(@PathVariable int inventor_id) {
        Inventor existingInventor = inventorService.findInventorById(inventor_id);
        if (existingInventor == null) {
            return ResponseEntity.notFound().build();
        }
        inventorService.deleteInventorById(inventor_id);
        return ResponseEntity.ok().build();
    }

}
