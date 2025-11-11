/**
 * ======================================================================
 * JUSTIFICATIVA GRASP: High Cohesion (Alta Coesão)
 * ======================================================================
 *
 * Por que esta classe existe?
 * Esta classe é um exemplo perfeito de Alta Coesão.
 *
 * A sua única responsabilidade é ser a "cara" do sistema no console.
 * Ela só sabe fazer duas coisas:
 * 1. Mostrar texto na tela (System.out.println)
 * 2. Ler o que o usuário digita (Scanner)
 *
 * Ela não tem *nenhuma* lógica de negócio. Ela não sabe o que é um
 * "Produto" ou como se calcula um "Pedido". Ela só exibe o que o
 * Controller manda e devolve o que o usuário digita.
 *
 * Vantagem: Se amanhã quiséssemos trocar este console por uma
 * interface gráfica (GUI), só teríamos de reescrever esta classe.
 * O Controller e o Model continuariam a funcionar da mesma forma.
 */

package pacote.view;

import java.util.Scanner;

/**
 * Classe responsável pela interação com o usuário.
 * Segue o princípio GRASP "Alta Coesão" — trata apenas da exibição e leitura de dados.
 */
public class MenuView {

    private final Scanner sc = new Scanner(System.in);

    // Menu principal
    public int menuPrincipal() {
        System.out.println("\n===============================");
        System.out.println("    BURGUER CODE SYSTEM     ");
        System.out.println("===============================");
        System.out.println("1 - Gerenciar Produtos (Cardápio)");
        System.out.println("2 - Gerenciar Pedidos (Atendimento)");
        System.out.println("0 - Sair");
        System.out.print(" Escolha uma opção: ");
        return sc.nextInt();
    }

    // Menu de produtos
    public int menuProdutos() {
        System.out.println("\n===============================");
        System.out.println("   GERENCIAMENTO DE PRODUTOS");
        System.out.println("===============================");
        System.out.println("1 - Cadastrar novo produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Atualizar produto");
        System.out.println("4 - Remover produto");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print(" Escolha uma opção: ");
        return sc.nextInt();
    }

    // Menu de pedidos
    public int menuPedidos() {
        System.out.println("\n===============================");
        System.out.println("    GERENCIAMENTO DE PEDIDOS");
        System.out.println("===============================");
        System.out.println("1 - Criar novo pedido");
        System.out.println("2 - Atualizar pedido (adicionar novos itens)");
        System.out.println("3 - Listar todos os pedidos");
        System.out.println("4 - Remover item de um pedido");
        System.out.println("5 - Cancelar pedido");
        System.out.println("6 - Finalizar pedido");
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("👉 Escolha uma opção: ");
        return sc.nextInt();
    }

    // --- NOVO MÉTODO ---
    /**
     * Menu para seleção de categoria de produto.
     */
    public int menuCategoria() {
        System.out.println("\n--- SELECIONE A CATEGORIA ---");
        System.out.println("1 - Bebida");
        System.out.println("2 - Porção");
        System.out.println("3 - Hamburguer"); // Sem acento, para consistência
        System.out.print(" Escolha uma opção: ");
        return sc.nextInt();
    }


    // Métodos auxiliares
    public String lerTexto(String msg) {
        System.out.print(msg);
        sc.nextLine(); // limpar buffer
        return sc.nextLine();
    }

    public double lerDouble(String msg) {
        System.out.print(msg);
        return sc.nextDouble();
    }

    public int lerInt(String msg) {
        System.out.print(msg);
        return sc.nextInt();
    }

    public void mensagem(String msg) {
        System.out.println(msg);
    }
}