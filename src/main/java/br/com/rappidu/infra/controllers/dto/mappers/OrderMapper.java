package br.com.rappidu.infra.controllers.dto.mappers;

import br.com.rappidu.infra.controllers.dto.request.OrderRequestDto;
import br.com.rappidu.infra.controllers.dto.response.OrderResponseDto;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.OrderRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderRequest toModel(OrderRequestDto orderRequestDto);

    OrderResponseDto toResponseDto(Order order);

    List<OrderResponseDto> toResponseDto(List<Order> order);
}
