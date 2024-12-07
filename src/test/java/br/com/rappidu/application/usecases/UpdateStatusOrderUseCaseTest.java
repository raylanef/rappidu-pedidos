package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.StatusOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateStatusOrderUseCaseTest {
    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order(1L, "John Doe", null,null,null,null,null);
        order.setStatus(StatusOrder.WAIT_PAYMENT); // Status inicial
    }

    @Test
    void testUpdateStatusSuccessfully() {
        StatusOrder newStatus = StatusOrder.RECEIVED; // Novo status a ser atribuído
        when(orderGateway.findByCode(1L)).thenReturn(order);
        when(orderGateway.save(order)).thenReturn(order);

        updateStatusOrderUseCase.updateStatus(1L, newStatus);

        assertEquals(newStatus, order.getStatus());  // Verifica se o status foi alterado
        verify(orderGateway, times(1)).save(order);  // Verifica se a ordem foi salva
    }


    @Test
    void testUpdateStatusWithSameStatus() {
        StatusOrder newStatus = StatusOrder.RECEIVED; // Status já definido
        when(orderGateway.findByCode(1L)).thenReturn(order);

        updateStatusOrderUseCase.updateStatus(1L, newStatus);

        assertEquals(StatusOrder.RECEIVED, order.getStatus());  // Verifica se o status não foi alterado
        verify(orderGateway, times(1)).save(order);  // O save ainda é chamado, mas sem alteração
    }
}