package pacote.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Aplica GRASP "Creator" — cria e gerencia seus próprios itens.
 */
public class Pedido {
    private int numero;
    private LocalDateTime dataHora;
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int numero) {
        this.numero = numero;
        this.dataHora = LocalDateTime.now();
    }

    public int getNumero() { return numero; }

    public void adicionarProduto(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(produto, quantidade);
        itens.add(item);
    }

    public boolean removerProduto(int idProduto) {
        return itens.removeIf(i -> i.getProduto().getId() == idProduto);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido i : itens) total += i.getSubtotal();
        return total;
    }

    public String resumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido nº ").append(numero).append(" - ").append(dataHora).append("\n");
        for (ItemPedido i : itens) sb.append(i).append("\n");
        sb.append("Total: R$").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }
}
