package br.com.rappidu.infra.controllers;


import br.com.rappidu.application.usecases.CreateOrderUseCase;
import br.com.rappidu.application.usecases.FindOrderUseCase;
import br.com.rappidu.application.usecases.PayOrderUseCase;
import br.com.rappidu.application.usecases.UpdateStatusOrderUseCase;
import br.com.rappidu.domain.entities.Order;
import br.com.rappidu.domain.entities.OrderRequest;
import br.com.rappidu.infra.controllers.dto.OrderUpdateRequestDTO;
import br.com.rappidu.infra.controllers.dto.RequestPaymentUpdate;
import br.com.rappidu.infra.controllers.dto.mappers.OrderMapper;
import br.com.rappidu.infra.controllers.dto.request.OrderRequestDto;
import br.com.rappidu.infra.controllers.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/orders")
@ComponentScan(
        basePackages = "br.com.rappidu.application.usecases",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {CreateOrderUseCase.class, FindOrderUseCase.class,
                        PayOrderUseCase.class, UpdateStatusOrderUseCase.class}
        )
)
@AllArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final UpdateStatusOrderUseCase updateStatusOrderUseCase;
    private final OrderMapper mapper;

    // TODO Aplicar paginação
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAll() {
        return ResponseEntity.ok(mapper.toResponseDto(findOrderUseCase.listAll()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponseDto> newOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderRequest request = mapper.toModel(orderRequestDto);
        Order order = createOrderUseCase.create(request);
        OrderResponseDto orderResponseDto = mapper.toResponseDto(order);

        orderResponseDto.add(linkTo(methodOn(PaymentController.class)
                .update(orderResponseDto.getCode(), null))
                .withSelfRel());

        return ResponseEntity.ofNullable(orderResponseDto);
    }

    @GetMapping("/{code}")
    public ResponseEntity<OrderResponseDto> findByCode(@PathVariable Long code) {
        var order = findOrderUseCase.findByCode(code);
        var responseDto = mapper.toResponseDto(order);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Atualização de status de pedido",
            description = "Atualizar status de pedido gerado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Pedido para ser atualizado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = OrderUpdateRequestDTO.class))))
    @PatchMapping("/{code}")
    public ResponseEntity<?> updateStatus(@PathVariable Long code, @RequestBody OrderUpdateRequestDTO orderUpdateRequest) {
        updateStatusOrderUseCase.updateStatus(code, orderUpdateRequest.statusOrder());
        return ResponseEntity.ok().build();
    }
}
