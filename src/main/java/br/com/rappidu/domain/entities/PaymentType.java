package br.com.rappidu.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PaymentType {
    DEBIT(1, "Débito"),
    CREDIT(2, "Crédito"),
    PIX(3, "Pix");

    private final int code;
    private final String description;

    private static final PaymentType[] VALUES = values();

    public static PaymentType getByCode(Integer code) {
        return Stream.of(VALUES)
                .filter(v -> v.code == code)
                .findFirst()
                .orElseThrow();
    }


}
