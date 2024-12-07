package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private FindOrderUseCase findOrderUseCase;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAllOrders() {
        Order order1 = new Order(1L, "John Doe",null,null,null,null, null);  // Simulação de ordem
        Order order2 = new Order(2L, "Jane Doe", null,null,null,null,null);
        List<Order> orders = Arrays.asList(order1, order2);

        when(orderGateway.findAll()).thenReturn(orders);
        List<Order> result = findOrderUseCase.listAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getCustomerName());
        verify(orderGateway, times(1)).findAll();
    }

    @Test
    void testFindOrderByCode() {
        Order order = new Order(1L, "Maricotinha",null,null,null,null, null);
        Long orderCode = 1L;
        when(orderGateway.findByCode(orderCode)).thenReturn(order);

        Order result = findOrderUseCase.findByCode(orderCode);

        assertNotNull(result);
        assertEquals(orderCode, result.getCode());
        assertEquals("Maricotinha", result.getCustomerName());
        verify(orderGateway, times(1)).findByCode(orderCode);
    }

    @Test
    void testFindOrderByCodeNotFound() {
        Long orderCode = 1L;

        when(orderGateway.findByCode(orderCode)).thenReturn(null);
        Order result = findOrderUseCase.findByCode(orderCode);

        assertNull(result);
        verify(orderGateway, times(1)).findByCode(orderCode);
    }
}