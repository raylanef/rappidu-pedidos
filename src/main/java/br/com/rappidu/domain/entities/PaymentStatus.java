package br.com.rappidu.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PAID(1, "Pago"),
    REFUSED(2, "Recusado"),
    PENDENT(3, "Pendente");

    private final int code;
    private final String description;

    private static final PaymentStatus[] VALUES = values();

    public static PaymentStatus getByCode(Integer code) {
        return Stream.of(VALUES)
                .filter(v -> v.code == code)
                .findFirst()
                .orElseThrow();
    }
}
