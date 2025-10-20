package com.resilient.payments.demo.dao;

import com.resilient.payments.demo.entity.Payment;

public interface PaymentsDao {


    public Payment getPayment(Long paymentId);

    public Payment createPayment(Payment payment);

    public Payment updatePayment(Payment payment);
}
