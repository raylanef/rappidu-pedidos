package br.com.rappidu.infra.persistence;

import br.com.rappidu.infra.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT o FROM OrderEntity o where o.status != 5 ORDER BY o.createAt ASC")
    Optional<List<OrderEntity>> findWhereStatusIsNotFinishedOrWaitPaymentOrderByCreateAtAsc();

    OrderEntity findByPaymentId(Long id);
}
