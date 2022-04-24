package com.publicis.sapient.creditcardsystem.repository.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="CREDIT_CARD")
@Data
public class CreditCardEntity implements Serializable {
    private static final long serialVersionUID = -5071334320180422452L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name="NAME")
    private String name;

    @Column(name="CARD_NUMBER")
    private String cardNumber;

    @Column(name="CARD_LIMIT", length = 19)
    private Long limit;

    @Column(name="BALANCE")
    private Float balance;
}
