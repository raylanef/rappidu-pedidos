package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.PaymentGateway;
import br.com.rappidu.domain.entities.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindPaymentUseCaseTest {
    @Mock
    private PaymentGateway paymentGateway;

    @InjectMocks
    private FindPaymentUseCase findPaymentUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getShouldReturnPaymentWhenPaymentExists() {
        Long paymentId = 123L;
        Payment expectedPayment = new Payment(paymentId, new BigDecimal(100.0), 3, 2);
        when(paymentGateway.findById(paymentId)).thenReturn(expectedPayment);

        Payment actualPayment = findPaymentUseCase.get(paymentId);
        verify(paymentGateway, times(1)).findById(paymentId);
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void getShouldThrowExceptionWhenPaymentDoesNotExist() {
        Long paymentId = 123L;
        when(paymentGateway.findById(paymentId)).thenThrow(new RuntimeException("Payment not found"));


        RuntimeException exception = assertThrows(RuntimeException.class, () -> findPaymentUseCase.get(paymentId));
        assertEquals("Payment not found", exception.getMessage());
        verify(paymentGateway, times(1)).findById(paymentId);
    }

}