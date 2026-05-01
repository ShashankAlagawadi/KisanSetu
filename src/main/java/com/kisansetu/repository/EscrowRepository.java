package com.kisansetu.repository;

import com.kisansetu.model.Escrow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscrowRepository extends MongoRepository<Escrow, String> {

    Optional<Escrow> findByOrderId(String orderId);

    Optional<Escrow> findByEscrowNumber(String escrowNumber);

    Optional<Escrow> findByTransactionId(String transactionId);
}
