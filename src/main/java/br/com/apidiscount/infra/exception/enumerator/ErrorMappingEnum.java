package br.com.apidiscount.infra.exception.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMappingEnum {

    USER_NOT_FOUND("Usuário não encontrado."),
    CATEGORY_NOT_FOUND("Categoria não encontrada."),
    ORDER_NOT_NULL("Campo orders_count não deve ser nulo."),
    CATEGORY_EXISTING("Já existe uma categoria que contempla este intervalo."),
    INVALID_CATEGORY_PARAMETERS("O máximo de pedidos deve ser maior que o mínimo"),
    REQUIRED_FIELD("Todos os campos são obrigatórios.");

    private final String message;

}
