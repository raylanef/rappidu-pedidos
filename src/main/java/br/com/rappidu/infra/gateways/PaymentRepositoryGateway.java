package br.com.rappidu.infra.gateways;

import br.com.rappidu.application.gateways.PaymentGateway;
import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.infra.mappers.PaymentEntityMapper;
import br.com.rappidu.infra.persistence.PaymentRepository;

import br.com.rappidu.infra.persistence.entities.PaymentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentRepositoryGateway implements PaymentGateway {

    private final PaymentRepository repository;
    private final PaymentEntityMapper mapper;

    @Override
    public void save(Payment payment) {
        var paymentEntity = mapper.toEntity(payment);
        repository.save(paymentEntity);
    }

    @Override
    public Payment findById(Long id) {
        PaymentEntity paymentEntity = repository.findById(id).orElseThrow();
        return mapper.toModel(paymentEntity);
    }
}
