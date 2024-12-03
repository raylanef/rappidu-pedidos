package br.com.rappidu.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public enum StatusOrder {
    WAIT_PAYMENT(1, "Aguardando o pagamento"),
    RECEIVED(2, "Recebido"),
    PREPARING(3, "Em preparo"),
    DONE(4, "Pronto"),
    FINISHED(5, "Finalizado");

    private final int code;
    private final String description;

    public static final StatusOrder[] VALUES = values();

    public static StatusOrder getByCode(int code) {
        return Stream.of(VALUES)
                .filter(v -> v.getCode() == code)
                .findFirst()
                .orElseThrow();
    }
}
