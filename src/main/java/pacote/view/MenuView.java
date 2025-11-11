package pacote.view;

import java.util.Scanner;

/**
 * Responsável pela interação com o usuário via console.
 * Alta coesão — lida apenas com exibição e entrada de dados.
 */
public class MenuView {
    private final Scanner sc = new Scanner(System.in);

    public int menuPrincipal() {
        System.out.println("\n=============================");
        System.out.println(" BURGUER CODE SYSTEM ");
        System.out.println("=============================");
        System.out.println("1 - Gerenciar Produtos");
        System.out.println("2 - Gerenciar Pedidos");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
        return sc.nextInt();
    }

    public int menuProdutos() {
        System.out.println("\n--- PRODUTOS ---");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Listar");
        System.out.println("3 - Atualizar");
        System.out.println("4 - Remover");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        return sc.nextInt();
    }

    public int menuPedidos() {
        System.out.println("\n--- PEDIDOS ---");
        System.out.println("1 - Criar novo pedido");
        System.out.println("2 - Adicionar item");
        System.out.println("3 - Listar pedidos");
        System.out.println("4 - Remover item");
        System.out.println("5 - Cancelar pedido");
        System.out.println("6 - Finalizar pedido");
        System.out.println("0 - Voltar");
        System.out.print("Escolha: ");
        return sc.nextInt();
    }

    // Métodos auxiliares de leitura
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
