package com.resilient.payments.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "partner_config", schema = "payments")
@Data
public class PartnerConfig {

  @Column(name = "partnerid")
  @Id
  @SequenceGenerator(
      name = "partner_id_seq_gen",
      sequenceName = "payments.partner_id_sequence",
      allocationSize = 1)
  private Long partnerId;

  private String partnerName;

  private String partnerRef;

  private String jwtSecret;

  private String apiKey;

  private String status;
}
