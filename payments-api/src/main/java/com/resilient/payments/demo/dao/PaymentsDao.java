package com.resilient.payments.demo.dao;

import com.resilient.payments.demo.entity.Payment;

public interface PaymentsDao {


    /**
     * Fetch Payment by Id
     * @param paymentId
     * @return
     */
    public Payment getPayment(Long paymentId);


    /**
     * Create Payment based on Payment Object
     * @param payment
     * @return
     */

    public Payment createPayment(Payment payment);

    /**
     * Update Payment based on Payment Object
     * @param payment
     * @return
     */

    public Payment updatePayment(Payment payment);
}
