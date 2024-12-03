package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.*;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CreateOrderUseCase  {

    private final OrderGateway orderGateway;

    public Order create(OrderRequest orderRequest) {
        Order order = buildOrder(orderRequest);
        return orderGateway.save(order);
    }

    private Order buildOrder(OrderRequest orderRequest) {
        List<Item> items = productRequestListToItems(orderRequest.products());
        return Order.create(orderRequest.customerName(), items);
    }

    private List<Item> productRequestListToItems(List<ProductRequest> productsRequest) {
        List<Item> items = new ArrayList<>();

        for (ProductRequest productRequest :  productsRequest) {
            Long code = productRequest.cod();
        /*    Product product = orderGateway.findProductByCode(productRequest.cod());

            Item item = new Item(code, product.name(), product.price(), productRequest.customs());
            items.add(item); */
        }
        return items;
    }
}
