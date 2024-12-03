package br.com.rappidu.infra.controllers.dto.request;

import java.util.List;

public record OrderRequestDto(String customerName, List<ProductRequestDto> products) {
}
