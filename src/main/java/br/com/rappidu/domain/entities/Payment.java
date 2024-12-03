package br.com.rappidu.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class Payment {

    private Long code;
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentType type;

    public Payment(BigDecimal amount) {
        this.amount = amount;
        this.status = PaymentStatus.PENDENT;
    }

    public Payment(Long code, BigDecimal amount, PaymentStatus status) {
        this.code = code;
        this.amount = amount;
        this.status = status;
    }

    public Payment(Long code, BigDecimal amount, Integer status, Integer type) {
        this.code = code;
        this.amount = amount;
        this.status = PaymentStatus.getByCode(status);
        this.type = PaymentType.getByCode(type);
    }
}
