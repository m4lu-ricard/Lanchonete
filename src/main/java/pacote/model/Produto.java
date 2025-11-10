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
