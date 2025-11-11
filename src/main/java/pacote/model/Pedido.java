package pacote.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; // Importar formatador
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Aplica GRASP "Creator" — cria e gerencia seus próprios itens.
 * Agora também gerencia seu próprio estado (StatusPedido).
 */
public class Pedido {
    private int numero;
    private LocalDateTime dataHora;
    private List<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status; // <-- NOVO CAMPO

    public Pedido(int numero) {
        this.numero = numero;
        this.dataHora = LocalDateTime.now();
        this.status = StatusPedido.EM_PREPARACAO; // <-- STATUS PADRÃO
    }

    public int getNumero() { return numero; }
    public StatusPedido getStatus() { return status; } // <-- NOVO GETTER

    // --- NOVO MÉTODO PARA VERIFICAR SE O PEDIDO ESTÁ VAZIO ---
    public boolean estaVazio() {
        return itens.isEmpty();
    }

    // --- NOVOS MÉTODOS PARA ALTERAR O STATUS ---
    public void finalizar() {
        this.status = StatusPedido.FINALIZADO;
    }

    public void cancelar() {
        this.status = StatusPedido.CANCELADO;
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        // Lógica para verificar se o item já existe
        Optional<ItemPedido> itemExistente = itens.stream()
                .filter(i -> i.getProduto().getId() == produto.getId())
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemPedido item = itemExistente.get();
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            ItemPedido item = new ItemPedido(produto, quantidade);
            itens.add(item);
        }
    }

    public String removerItem(int idProduto, int qtdParaRemover) {
        if (qtdParaRemover <= 0) {
            return "Quantidade inválida. Deve ser maior que zero.";
        }

        Optional<ItemPedido> itemEncontrado = itens.stream()
                .filter(i -> i.getProduto().getId() == idProduto)
                .findFirst();

        if (itemEncontrado.isEmpty()) {
            return "Item com ID " + idProduto + " não encontrado neste pedido.";
        }

        ItemPedido item = itemEncontrado.get();
        int qtdAtual = item.getQuantidade();
        String nomeProduto = item.getProduto().getNome();

        if (qtdParaRemover >= qtdAtual) {
            itens.remove(item);
            return "Item '" + nomeProduto + "' (todos " + qtdAtual + ") removido do pedido.";
        }

        int novaQtd = qtdAtual - qtdParaRemover;
        item.setQuantidade(novaQtd);
        return qtdParaRemover + "x '" + nomeProduto + "' removido(s). Restam " + novaQtd + ".";
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido i : itens) total += i.getSubtotal();
        return total;
    }

    public String resumo() {
        // Define um formatador para a data/hora ficar mais legível
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append("------------------------------\n");
        sb.append("Pedido nº: ").append(numero);
        sb.append(" (").append(dataHora.format(formatador)).append(")\n");
        
        // --- MOSTRA O STATUS COM DESTAQUE ---
        sb.append(">>> STATUS: ").append(status.getDescricao()).append(" <<<\n");
        
        if (itens.isEmpty()) {
            sb.append("  (Vazio)\n"); // Atualizado para mostrar (Vazio)
        } else {
            sb.append("Itens:\n");
            for (ItemPedido i : itens) sb.append("  ").append(i).append("\n");
        }
        sb.append("Total: R$").append(String.format("%.2f", calcularTotal()));
        sb.append("\n------------------------------");
        return sb.toString();
    }
}