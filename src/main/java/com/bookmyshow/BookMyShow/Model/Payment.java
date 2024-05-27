package com.bookmyshow.BookMyShow.Model;

import com.bookmyshow.BookMyShow.Model.Enums.PaymentMethod;
import com.bookmyshow.BookMyShow.Model.Enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Payment extends BaseModel{

    private LocalDateTime paymentTime;

    private double amount;

    private String referenceId;

    @ManyToOne
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
