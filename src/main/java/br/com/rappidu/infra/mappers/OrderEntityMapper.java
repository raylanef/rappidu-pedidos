package br.com.rappidu.infra.mappers;

import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.PaymentStatus;
import br.com.rappidu.domain.entities.PaymentType;
import br.com.rappidu.domain.entities.StatusOrder;
import br.com.rappidu.infra.persistence.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemEntityMapper.class, PaymentEntityMapper.class})
public interface OrderEntityMapper {


    @Mapping(target = "id", source = "code")
    OrderEntity toEntity(Order order);

    @Mapping(target = "code", source = "id")
    Order toModel(OrderEntity entity);

    @Mapping(target = "code", source = "id")
    List<Order> toModel(List<OrderEntity> entity);

    default StatusOrder getStatusOrder(Integer code) {
        return StatusOrder.getByCode(code);
    }
}
