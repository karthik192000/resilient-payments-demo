package com.resilient.payments.demo.repository;

import com.resilient.payments.demo.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * * * Payments Repository Interface
 */
@Repository
public interface PaymentsRepository extends JpaRepository<Payment,Long> {
}
