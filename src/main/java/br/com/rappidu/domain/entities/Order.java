package br.com.rappidu.domain.entities;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {

    private final Long code;
    private final String customerName;
    private final BigDecimal amount;
    private final List<Item> items;
    private final LocalDateTime createAt;
    private final Payment payment;

    @Setter
    private StatusOrder status;


    public static Order create(String customerName, List<Item> items) {
        BigDecimal amount = items.stream()
                .map(Item::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Order(null, customerName, amount, items,
                LocalDateTime.now(), new Payment(amount), StatusOrder.WAIT_PAYMENT);
    }

    public Order(Long code, String customerName, BigDecimal amount, List<Item> items,
                 LocalDateTime createAt, Payment payment, StatusOrder status) {
        this.code = code;
        this.customerName = customerName;
        this.amount = amount;
        this.items = items;
        this.createAt = createAt;
        this.payment = payment;
        this.status = status;
    }

    public void pay() {
        this.status = StatusOrder.RECEIVED;
    }

}
