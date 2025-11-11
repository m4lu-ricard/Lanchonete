/**
 * ======================================================================
 * JUSTIFICATIVA GRASP: High Cohesion (Alta Coesão)
 * ======================================================================
 *
 * Por que esta classe existe?
 * Esta classe é um exemplo simples e direto de Alta Coesão.
 *
 * A sua única responsabilidade é "ser" um Produto (ou um ItemPedido).
 * Ela apenas guarda os seus próprios dados (ID, nome, preço, etc.).
 *
 * Ela não tenta fazer coisas que não são da sua conta, como imprimir-se
 * no menu (isso é a View que faz) ou controlar o fluxo do pedido (isso
 * é o Controller). Ela só representa um "dado" do nosso sistema,
 * o que a torna fácil de entender e reutilizar.
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