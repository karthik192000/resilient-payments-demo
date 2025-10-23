package com.resilient.payments.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;



/**
 * Payment Entity Class
 */
@Entity
@Table(name = "payments",schema = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private Long paymentId;

    @Column(name = "paymentreference")
    private String paymentReference;

    @Column(name = "sendername")
    private String senderName;

    @Column(name = "senderaccountnumber")
    private Integer senderAccountNumber;

    @Column(name = "receivername")
    private String receiverName;

    @Column(name = "receiveraccountnumber")
    private Integer receiverAccountNumber;

    @Column(name = "switchreference")
    private String switchReference;

    @Column(name = "createddt")
    private Timestamp createdDt;

    @Column(name = "updateddt")
    private Timestamp updatedDt;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "finalstatusdt")
    private Timestamp finalstatusdt;

    @Column(name = "reconjobid")
    private String reconjobid;
}
