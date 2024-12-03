package br.com.rappidu.infra.mappers;

import br.com.rappidu.domain.entities.Payment;
import br.com.rappidu.domain.entities.PaymentStatus;

import br.com.rappidu.domain.entities.PaymentType;
import br.com.rappidu.infra.persistence.entities.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    @Mapping(source = "code", target = "id")
    PaymentEntity toEntity(Payment payment);

    @Mapping(target = "code", source = "id")
    Payment toModel(PaymentEntity paymentEntities);

    default PaymentStatus getStatus(Integer integer) {
        return PaymentStatus.getByCode(integer);
    }

    default PaymentType getType(Integer code) {
        if(code != null) {
            return PaymentType.getByCode(code);
        }
        return null;
    }

}
