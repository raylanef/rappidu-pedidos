package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.StatusOrder;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UpdateStatusOrderUseCase {

    private final OrderGateway orderGateway;

    public void updateStatus(Long id, StatusOrder statusOrder) {
        Order order = orderGateway.findByCode(id);
        order.setStatus(statusOrder);

        orderGateway.save(order);
    }
}
