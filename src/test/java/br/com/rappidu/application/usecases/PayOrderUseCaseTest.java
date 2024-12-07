package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static br.com.rappidu.domain.entities.PaymentStatus.PAID;
import static br.com.rappidu.domain.entities.StatusOrder.RECEIVED;
import static br.com.rappidu.domain.entities.StatusOrder.WAIT_PAYMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PayOrderUseCaseTest {

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private PayOrderUseCase payOrderUseCase;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order(1L, "John Doe",null,null,null,null, null);
        order.setStatus(WAIT_PAYMENT); // Defina um status inicial
    }

    @Test
    void testPayOrder() {
        Long orderId = 1L;
        when(orderGateway.findByCode(orderId)).thenReturn(order);
        when(orderGateway.save(order)).thenReturn(order);

        Order paidOrder = payOrderUseCase.pay(orderId);

        assertNotNull(paidOrder);
        assertEquals(RECEIVED, paidOrder.getStatus()); // Verifica se o status foi alterado para PAID
        verify(orderGateway, times(1)).findByCode(orderId);
        verify(orderGateway, times(1)).save(order);
    }

    @Test
    void testOrderPayStatusChange() {
        Long orderId = 1L;
        when(orderGateway.findByCode(orderId)).thenReturn(order);
        when(orderGateway.save(order)).thenReturn(order);


        payOrderUseCase.pay(orderId);

        assertEquals(RECEIVED, order.getStatus()); // Verifica se o status foi alterado para PAID
    }
}