package br.com.rappidu.infra.persistence;

import br.com.rappidu.infra.persistence.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
