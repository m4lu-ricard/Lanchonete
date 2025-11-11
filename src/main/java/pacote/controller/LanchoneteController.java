package pacote.controller;

import pacote.model.*;
import pacote.view.MenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Controladora do sistema — aplica o padrão GRASP Controller.
 * Coordena as interações entre a View e o Model.
 */
public class LanchoneteController {

    private final MenuView view = new MenuView();
    private final List<Produto> produtos = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private int nextProdutoId = 1;
    private int nextPedidoId = 1;

    // --- CONSTRUTOR ---
    // Carrega automaticamente o cardápio inicial ao iniciar o sistema.
    public LanchoneteController() {
        carregarProdutosIniciais();
    }

    // --- MÉTODO PRINCIPAL ---
    public void iniciarSistema() {
        int opcao;
        do {
            opcao = view.menuPrincipal();
            switch (opcao) {
                case 1 -> gerenciarProdutos();
                case 2 -> gerenciarPedidos();
                case 0 -> view.mensagem("Encerrando o sistema...");
                default -> view.mensagem("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    /* ---------------------- CRUD PRODUTOS ---------------------- */
    private void gerenciarProdutos() {
        int opcao;
        do {
            opcao = view.menuProdutos();
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> atualizarProduto();
                case 4 -> removerProduto();
                case 0 -> view.mensagem("Voltando ao menu principal...");
                default -> view.mensagem("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarProduto() {
        String nome = view.lerTexto("Nome do produto: ");
        double preco = view.lerDouble("Preço: ");
        String categoria = view.lerTexto("Categoria: ");
        Produto p = new Produto(nextProdutoId++, nome, preco, categoria);
        produtos.add(p);
        view.mensagem("Produto cadastrado com sucesso!");
    }

    private void listarProdutos() {
        if (produtos.isEmpty()) view.mensagem("Nenhum produto cadastrado.");
        else {
            view.mensagem("=== CARDÁPIO ATUAL ===");
            produtos.forEach(System.out::println);
        }
    }

    private void atualizarProduto() {
        listarProdutos();
        int id = view.lerInt("Digite o ID do produto a atualizar: ");
        Produto produto = produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (produto == null) {
            view.mensagem("Produto não encontrado.");
            return;
        }

        String novoNome = view.lerTexto("Novo nome (" + produto.getNome() + "): ");
        double novoPreco = view.lerDouble("Novo preço (" + produto.getPreco() + "): ");
        String novaCat = view.lerTexto("Nova categoria (" + produto.getCategoria() + "): ");
        produto.setNome(novoNome);
        produto.setPreco(novoPreco);
        produto.setCategoria(novaCat);
        view.mensagem("Produto atualizado!");
    }

    private void removerProduto() {
        listarProdutos();
        int id = view.lerInt("Digite o ID do produto a remover: ");
        boolean removido = produtos.removeIf(p -> p.getId() == id);
        view.mensagem(removido ? "Produto removido com sucesso." : "ID não encontrado.");
    }

    /* ---------------------- CRUD PEDIDOS ---------------------- */
    private void gerenciarPedidos() {
        int opcao;
        do {
            opcao = view.menuPedidos();
            switch (opcao) {
                case 1 -> criarPedido();
                case 2 -> atualizarPedido();
                case 3 -> listarPedidos();
                case 4 -> removerItem();
                case 5 -> cancelarPedido();
                case 6 -> finalizarPedido();
                case 0 -> view.mensagem("Voltando ao menu principal...");
                default -> view.mensagem("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Criação do pedido + adição de itens no mesmo fluxo
    private void criarPedido() {
        if (produtos.isEmpty()) {
            view.mensagem("Nenhum produto cadastrado! Cadastre produtos antes de criar pedidos.");
            return;
        }

        Pedido pedido = new Pedido(nextPedidoId++);
        pedidos.add(pedido);
        view.mensagem("Pedido nº " + pedido.getNumero() + " criado!");
        view.mensagem("Agora, adicione os itens ao pedido.");

        boolean adicionando = true;
        while (adicionando) {
            listarProdutos();
            int idProd = view.lerInt("Digite o ID do produto (0 para finalizar): ");
            if (idProd == 0) {
                adicionando = false;
                break;
            }

            Produto prod = produtos.stream()
                    .filter(p -> p.getId() == idProd)
                    .findFirst()
                    .orElse(null);

            if (prod == null) {
                view.mensagem("Produto não encontrado. Tente novamente.");
                continue;
            }

            int qtd = view.lerInt("Quantidade: ");
            if (qtd <= 0) {
                view.mensagem("Quantidade inválida!");
                continue;
            }

            pedido.adicionarProduto(prod, qtd);
            view.mensagem(qtd + "x " + prod.getNome() + " adicionado(s) ao pedido.");
        }

        view.mensagem("Pedido nº " + pedido.getNumero() + " criado com sucesso!");
        view.mensagem(pedido.resumo());
    }

    // Atualiza um pedido já existente (adiciona novos itens)
    private void atualizarPedido() {
        if (pedidos.isEmpty()) {
            view.mensagem("Nenhum pedido criado ainda!");
            return;
        }

        listarPedidos();
        int num = view.lerInt("Digite o número do pedido que deseja atualizar: ");
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getNumero() == num)
                .findFirst()
                .orElse(null);

        if (pedido == null) {
            view.mensagem("Pedido não encontrado.");
            return;
        }

        if (produtos.isEmpty()) {
            view.mensagem("Nenhum produto cadastrado! Cadastre produtos antes de atualizar pedidos.");
            return;
        }

        view.mensagem("Atualizando Pedido nº " + pedido.getNumero());

        boolean adicionando = true;
        while (adicionando) {
            listarProdutos();
            int idProd = view.lerInt("Digite o ID do produto (0 para encerrar): ");
            if (idProd == 0) {
                adicionando = false;
                break;
            }

            Produto prod = produtos.stream()
                    .filter(p -> p.getId() == idProd)
                    .findFirst()
                    .orElse(null);

            if (prod == null) {
                view.mensagem("Produto não encontrado. Tente novamente.");
                continue;
            }

            int qtd = view.lerInt("Quantidade: ");
            if (qtd <= 0) {
                view.mensagem("Quantidade inválida!");
                continue;
            }

            pedido.adicionarProduto(prod, qtd);
            view.mensagem(qtd + "x " + prod.getNome() + " adicionado(s) ao pedido.");
        }

        view.mensagem("Pedido nº " + pedido.getNumero() + " atualizado com sucesso!");
        view.mensagem(pedido.resumo());
    }

    private void listarPedidos() {
        if (pedidos.isEmpty()) view.mensagem("Nenhum pedido registrado.");
        else pedidos.forEach(p -> view.mensagem(p.resumo() + "\n"));
    }

    private void removerItem() {
        listarPedidos();
        int num = view.lerInt("Número do pedido: ");
        Pedido pedido = pedidos.stream().filter(p -> p.getNumero() == num).findFirst().orElse(null);
        if (pedido == null) {
            view.mensagem("Pedido não encontrado.");
            return;
        }

        int idProd = view.lerInt("ID do produto para remover: ");
        boolean ok = pedido.removerProduto(idProd);
        view.mensagem(ok ? "Item removido." : "Item não encontrado.");
    }

    private void cancelarPedido() {
        listarPedidos();
        int num = view.lerInt("Número do pedido a cancelar: ");
        boolean ok = pedidos.removeIf(p -> p.getNumero() == num);
        view.mensagem(ok ? "Pedido cancelado." : "Pedido não encontrado.");
    }

    private void finalizarPedido() {
        listarPedidos();
        int num = view.lerInt("Número do pedido para finalizar: ");
        Pedido pedido = pedidos.stream().filter(p -> p.getNumero() == num).findFirst().orElse(null);
        if (pedido == null) {
            view.mensagem("Pedido não encontrado.");
            return;
        }

        view.mensagem("Resumo Final:\n" + pedido.resumo());
        pedidos.remove(pedido);
        view.mensagem("Pedido finalizado e removido da lista ativa.");
    }

    /* ---------------------- CARDÁPIO INICIAL ---------------------- */
    private void carregarProdutosIniciais() {
        // Categoria: Hambúrguer
        produtos.add(new Produto(nextProdutoId++, "Pombo Da Paz", 29.99, "Hambúrguer"));
        produtos.add(new Produto(nextProdutoId++, "60%", 40.00, "Hambúrguer"));
        produtos.add(new Produto(nextProdutoId++, "C Program Burguer", 19.72, "Hambúrguer"));
        produtos.add(new Produto(nextProdutoId++, "Redbull Burguer", 33.00, "Hambúrguer"));
        produtos.add(new Produto(nextProdutoId++, "Vscode Burguer", 25.00, "Hambúrguer"));
        produtos.add(new Produto(nextProdutoId++, "Java Lovers", 25.00, "Hambúrguer"));

        // Categoria: Porção
        produtos.add(new Produto(nextProdutoId++, "Batata Frita Tradicional", 20.00, "Porção"));
        produtos.add(new Produto(nextProdutoId++, "Batata Frita Com Cheddar", 26.00, "Porção"));
        produtos.add(new Produto(nextProdutoId++, "Onion Rings", 25.00, "Porção"));
        produtos.add(new Produto(nextProdutoId++, "Dadinho de Tapioca", 22.00, "Porção"));

        // Categoria: Bebida
        produtos.add(new Produto(nextProdutoId++, "Refrigerante Lata (350ml)", 5.00, "Bebida"));
        produtos.add(new Produto(nextProdutoId++, "Água Mineral (500ml)", 4.00, "Bebida"));
        produtos.add(new Produto(nextProdutoId++, "Suco Natural", 8.00, "Bebida"));
        produtos.add(new Produto(nextProdutoId++, "Redbull (250ml)", 8.00, "Bebida"));
    }
}
