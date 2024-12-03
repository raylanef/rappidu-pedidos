package br.com.rappidu.application.usecases;

import br.com.rappidu.application.gateways.PaymentGateway;
import br.com.rappidu.domain.entities.Payment;
import lombok.AllArgsConstructor;




@AllArgsConstructor
public class FindPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public Payment get(Long id) {
        return paymentGateway.findById(id);
    }
}
