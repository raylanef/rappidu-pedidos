package br.com.rappidu.infra.controllers.dto.response;

import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.domain.entities.StatusOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OrderResponseDto extends RepresentationModel<OrderResponseDto> {

    private final Long code;
    private final String customerName;
    private final BigDecimal amount;
    private final StatusOrder status;
    private final LocalDateTime createAt;
    private final Payment payment;

    public OrderResponseDto(Long code, String customerName, BigDecimal amount, StatusOrder status, LocalDateTime createAt, Payment payment) {
        this.code = code;
        this.amount = amount;
        this.status = status;
        this.customerName = customerName;
        this.createAt = createAt;
        this.payment = payment;
    }
}
