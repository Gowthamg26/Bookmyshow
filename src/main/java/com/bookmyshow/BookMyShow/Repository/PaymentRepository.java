package com.bookmyshow.BookMyShow.Repository;

import com.bookmyshow.BookMyShow.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}