package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.*;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PayOrderUseCase {

    private final OrderGateway orderGateway;

    public Order pay(Long oderId) {
        Order order = orderGateway.findByCode(oderId);
        order.pay();
        return orderGateway.save(order);
    }

}
