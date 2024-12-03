package br.com.rappidu.infra.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record RequestPaymentUpdate(BigDecimal amount,
                                   @Schema(description = "1 - DEBITO, 2 - CREDITO, 3 - PIX") Integer type,
                                   @Schema(description = "1 - PAGO, 2 - RECUSADO, 3 - PEDENTE") Integer status) {

}
