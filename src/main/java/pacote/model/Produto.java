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
