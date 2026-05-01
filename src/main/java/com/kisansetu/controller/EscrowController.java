package com.kisansetu.controller;

import com.kisansetu.model.Escrow;
import com.kisansetu.repository.EscrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/escrow")
@CrossOrigin(origins = "*")
public class EscrowController {

    private final EscrowRepository escrowRepository;

    @Autowired
    public EscrowController(EscrowRepository escrowRepository) {
        this.escrowRepository = escrowRepository;
    }

    // Get all escrow records
    @GetMapping
    public ResponseEntity<List<Escrow>> getAllEscrows() {
        return ResponseEntity.ok(escrowRepository.findAll());
    }

    // Get escrow by ID
    @GetMapping("/{id}")
    public ResponseEntity<Escrow> getEscrowById(@PathVariable String id) {
        Optional<Escrow> escrow = escrowRepository.findById(id);
        return escrow.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get escrow by order ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Escrow> getEscrowByOrderId(@PathVariable String orderId) {
        Optional<Escrow> escrow = escrowRepository.findByOrderId(orderId);
        return escrow.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get escrow by escrow number
    @GetMapping("/number/{escrowNumber}")
    public ResponseEntity<Escrow> getEscrowByNumber(@PathVariable String escrowNumber) {
        Optional<Escrow> escrow = escrowRepository.findByEscrowNumber(escrowNumber);
        return escrow.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
