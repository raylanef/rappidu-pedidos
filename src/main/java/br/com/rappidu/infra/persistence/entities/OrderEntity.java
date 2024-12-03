package br.com.rappidu.infra.persistence.entities;

import br.com.rappidu.domain.entities.StatusOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private List<ItemEntity> items = new ArrayList<>();

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity payment;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }

    public void setStatus(StatusOrder status) {
        this.status = status.getCode();
    }

}
