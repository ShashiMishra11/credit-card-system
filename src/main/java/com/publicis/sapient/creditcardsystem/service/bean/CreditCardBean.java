package com.publicis.sapient.creditcardsystem.service.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Component
@NoArgsConstructor
@Data
public class CreditCardBean implements Serializable {
    private static final long serialVersionUID = 5925537401896500932L;

    private String name;
    @Max(19)
    @Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Card Number should be numeric")
    private String cardNumber;
    private Long limit;
    private float balance;

    public CreditCardBean(String name, String cardNumber, Long limit, float balance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.limit = limit;
        this.balance = balance;
    }
}
