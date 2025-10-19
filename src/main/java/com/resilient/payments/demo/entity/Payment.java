package com.resilient.payments.demo.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "payments",schema = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private Long paymentId;

    @Column(name = "paymentreference", nullable = false, length = 255)
    private String paymentReference;

    @Column(name = "sendername", nullable = false, length = 255)
    private String senderName;

    @Column(name = "senderaccountnumber", nullable = false)
    private Integer senderAccountNumber;

    @Column(name = "receivername", nullable = false, length = 255)
    private String receiverName;

    @Column(name = "receiveraccountnumber", nullable = false)
    private Integer receiverAccountNumber;

    @Column(name = "switchreference", nullable = false, length = 255)
    private String switchReference;

    @Column(name = "createddt", nullable = false)
    private Timestamp createdDt;

    @Column(name = "updateddt", nullable = false)
    private Timestamp updatedDt;

    @Column(name = "status", nullable = false, length = 50)
    private String status;
}
