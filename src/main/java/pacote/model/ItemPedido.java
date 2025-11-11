package pacote.model;

/**
 * Representa um produto dentro de um pedido.
 */
public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    // --- NOVO MÉTODO ---
    // Permite que o Pedido altere a quantidade (para remoção parcial)
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        // --- MUDANÇA AQUI ---
        // Agora exibe o ID do produto para facilitar a remoção
        return "[ID " + produto.getId() + "] " + 
               quantidade + "x " + 
               produto.getNome() + "  R$" + 
               String.format("%.2f", getSubtotal());
    }
}