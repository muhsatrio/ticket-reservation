package com.app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.project.repository.dto.TransactionDTO;
import com.app.project.repository.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findBySaleEventIdAndDeletedAtIsNull(String saleEventId);
    Transaction findByIdAndDeletedAtIsNull(String id);

    @Query(value = "SELECT t.email, t.phone, c.name as identityName, c.identity_number as identityNumber, c.identity_type as identityType, t.created_at as orderDate, t.status, t.total_payment as totalPayment FROM transaction t INNER JOIN customer c on t.id = c.transaction_id WHERE t.deleted_at IS NULL AND c.deleted_at IS NULL", nativeQuery = true)
    List<TransactionDTO> findSummary(String id);
}
