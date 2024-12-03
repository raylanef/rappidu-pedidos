package br.com.rappidu.infra.persistence.entities;

import br.com.rappidu.domain.entities.PaymentStatus;
import br.com.rappidu.domain.entities.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "PAYMENTS")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "TYPE")
    private Integer type;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private OrderEntity order;


    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }

    public void setType(PaymentType type) {
        if(type != null) {
            this.type = type.getCode();
        }
    }
}
