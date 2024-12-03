package br.com.rappidu.application.gateways;

import br.com.rappidu.domain.entities.Payment;

public interface PaymentGateway  {

    void save(Payment payment);
    Payment findById(Long id);
}
