package br.com.rappidu.application.gateways;

import br.com.rappidu.domain.entities.Order;


import java.util.List;

public interface OrderGateway {
    Order save(Order order);
   // Product findProductByCode(Long id);
    Order findByCode(Long id);
    List<Order> findAll();
    Order findByPaymentCode(Long code);
}
