package com.resilient.payments.demo.repository;

import com.resilient.payments.demo.entity.PartnerConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerConfigRepository extends JpaRepository<PartnerConfig, Long> {}
