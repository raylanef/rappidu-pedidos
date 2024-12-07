package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.Item;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.OrderRequest;
import br.com.rappidu.domain.entities.ProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateOrderUseCaseTest {
    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        ProductRequest productRequest1 = new ProductRequest(1L, "Customization1");
        ProductRequest productRequest2 = new ProductRequest(2L, "Customization2");

        List<ProductRequest> productRequests = Arrays.asList(productRequest1, productRequest2);

        OrderRequest orderRequest = new OrderRequest("John Doe", productRequests);
        List<Item> items = Arrays.asList(
                new Item(1L, "Product1", new BigDecimal(10.0), "Customization1"),
                new Item(2L, "Product2", new BigDecimal(20.0), "Customization2")
        );

        Order expectedOrder = Order.create("John Doe", items);

        when(orderGateway.save(any(Order.class))).thenReturn(expectedOrder);

        Order createdOrder = createOrderUseCase.create(orderRequest);

        assertEquals(expectedOrder, createdOrder);
        verify(orderGateway, times(1)).save(any(Order.class));
    }

    @Test
    void testBuildOrder() {
        ProductRequest productRequest1 = new ProductRequest(1L, "Customization1");
        ProductRequest productRequest2 = new ProductRequest(2L, "Customization2");

        List<ProductRequest> productRequests = Arrays.asList(productRequest1, productRequest2);

        OrderRequest orderRequest = new OrderRequest("Jane Doe", productRequests);

        when(orderGateway.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order createdOrder = createOrderUseCase.create(orderRequest);

        assertEquals("Jane Doe", createdOrder.getCustomerName());
        verify(orderGateway, times(1)).save(any(Order.class));
    }


}