package pacote.model;

/**
 * Representa os estados possíveis de um Pedido.
 * Usar um Enum é uma boa prática para ter um conjunto fixo de constantes.
 */
public enum StatusPedido {
    EM_PREPARACAO("Em preparação"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}