package com.accountmovementservice.accountmovementservice.controller;

import com.accountmovementservice.accountmovementservice.dtos.movement.MovementBody;
import com.accountmovementservice.accountmovementservice.dtos.movement.MovementResponse;
import com.accountmovementservice.accountmovementservice.dtos.Response;
import com.accountmovementservice.accountmovementservice.service.Movement.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovementServiceController {


    private final MovementService movementService;

    @Autowired
    public MovementServiceController(MovementService movementService) {
        this.movementService = movementService;
    }


    @GetMapping(value = "/Movimientos/{movementId}")
    public ResponseEntity<MovementResponse> getMovement(@PathVariable(name = "movementId") Integer movementId) {
        MovementResponse movement = movementService.getMovement(movementId);
        return movement != null ? ResponseEntity.ok(movement) : ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/Movimientos")
    public ResponseEntity<Response> addMovement(@RequestBody MovementBody movement) {
        boolean created = movementService.createMovement(movement);
        return created ? ResponseEntity.ok(new Response(created, "Created Movement")) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping(value = "/Movimientos/{movementId}")
    public ResponseEntity<Response> deleteMovement(@PathVariable(name = "movementId") Integer movementId) {
        boolean deleted = movementService.deleteMovement(movementId);
        return deleted ? ResponseEntity.ok(new Response(deleted, "Deleted Movement")) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/Movimientos/{movementId}")
    public ResponseEntity<Response> updateMovement(@PathVariable(name = "movementId") Integer accountId, @RequestBody MovementBody movement) {
        boolean updated = movementService.editMovement(movement, accountId);
        return updated ? ResponseEntity.ok(new Response(updated, "Updated Movement")) : ResponseEntity.badRequest().build();
    }

}
