package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.application.gateways.PaymentGateway;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.domain.entities.PaymentStatus;
import br.com.rappidu.infra.exceptions.InvalidPaymentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static br.com.rappidu.domain.entities.StatusOrder.RECEIVED;
import static br.com.rappidu.domain.entities.StatusOrder.WAIT_PAYMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdatePaymentUseCaseTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private OrderGateway orderGateway;

    @InjectMocks
    private UpdatePaymentUseCase updatePaymentUseCase;

    private Payment paymentRequest;
    private Payment paymentDb;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentRequest = new Payment(1L, BigDecimal.valueOf(100), PaymentStatus.PAID);

        paymentDb = new Payment(1L, BigDecimal.valueOf(100), PaymentStatus.PENDENT);

        order = new Order(1L, "John Doe",null,null,null,null, null);
        order.setStatus(WAIT_PAYMENT);
    }

    @Test
    void testUpdatePaymentSuccessfully() {
        when(paymentGateway.findById(paymentRequest.getCode())).thenReturn(paymentDb);
        when(orderGateway.findByPaymentCode(paymentRequest.getCode())).thenReturn(order);
        when(orderGateway.save(order)).thenReturn(order);

        updatePaymentUseCase.update(paymentRequest);

        assertEquals(RECEIVED, order.getStatus());  // Verifica se o status da ordem foi alterado
        verify(paymentGateway, times(1)).save(paymentRequest);  // Verifica se o pagamento foi salvo
        verify(orderGateway, times(1)).save(order);  // Verifica se a ordem foi salva
    }

    @Test
    void testUpdatePaymentWithInvalidAmount() {
        paymentRequest.setAmount(BigDecimal.valueOf(150));  // Valor incorreto
        when(paymentGateway.findById(paymentRequest.getCode())).thenReturn(paymentDb);

        InvalidPaymentException exception = assertThrows(InvalidPaymentException.class, () -> {
            updatePaymentUseCase.update(paymentRequest);
        });
        assertEquals("O valor pago é diferente do esperado", exception.getMessage());
        verify(paymentGateway, times(0)).save(any(Payment.class));  // O pagamento não deve ser salvo
        verify(orderGateway, times(0)).save(any(Order.class));  // A ordem não deve ser salva
    }

}