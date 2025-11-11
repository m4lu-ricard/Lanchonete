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
 * Representa um produto do cardápio.
 * Alta coesão: cuida apenas dos dados de produto.
 */
public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String categoria;

    public Produto(int id, String nome, double preco, String categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getCategoria() { return categoria; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + categoria + ") R$" + String.format("%.2f", preco);
    }
}
