package br.com.rappidu.infra.controllers.dto;

import br.com.rappidu.domain.entities.StatusOrder;

public record OrderUpdateRequestDTO(StatusOrder statusOrder) {
}
