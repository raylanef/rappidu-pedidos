package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.*;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindOrderUseCase {

    private final OrderGateway orderGateway;

    public List<Order> listAll() {
        return orderGateway.findAll();
    }

    public Order findByCode(Long code) {
        return orderGateway.findByCode(code);
    }

}
