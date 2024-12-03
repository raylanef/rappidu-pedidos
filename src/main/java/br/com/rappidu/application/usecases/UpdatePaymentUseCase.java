package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.OrderGateway;
import br.com.rappidu.application.gateways.PaymentGateway;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.domain.entities.PaymentStatus;
import br.com.rappidu.infra.exceptions.InvalidPaymentException;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class UpdatePaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final OrderGateway orderGateway;

    @Transactional
    public void update(Payment paymentRequest) {
        Payment paymentDb = paymentGateway.findById(paymentRequest.getCode());

        if(paymentRequest.getStatus() == PaymentStatus.PAID) {
            if (paymentRequest.getAmount().compareTo(paymentDb.getAmount()) != 0) {
                throw new InvalidPaymentException("O valor pago é diferente do esperado");
            }
            Order order = orderGateway.findByPaymentCode(paymentRequest.getCode());
            order.pay();
            orderGateway.save(order);
        }
        paymentGateway.save(paymentRequest);
    }
}
