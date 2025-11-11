/**
 * =================================================================================
 *  JUSTIFICATIVA GRASP: HIGH COHESION (Alta Coesão)
 * =================================================================================
 * *  QUAL PADRÃO?
 * GRASP High Cohesion (Alta Coesão).
 * *  ONDE FOI APLICADO?
 * Esta classe inteira (Produto / ItemPedido) aplica o padrão.
 * *  POR QUÊ?
 * Esta classe tem uma responsabilidade única e bem definida:
 * (Para Produto): Apenas representar os dados de um produto do cardápio.
 * (Para ItemPedido): Apenas representar um produto dentro de um pedido, 
 * com sua quantidade.
 * * Ela não se preocupa em como é exibida na 'View' ou como o 'Controller'
 * gerencia o fluxo. Ao manter o foco em uma única tarefa, a classe se torna
 * mais fácil de entender, manter e reutilizar.
 * * =================================================================================
 */

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