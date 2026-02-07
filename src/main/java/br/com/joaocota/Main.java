package br.com.joaocota;


import java.io.Console;

import java.util.List;


public class Main {

    static Console console = System.console();
    public static void main(String[] args) {
        
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(console.readLine());
            switch (opcao) {
                case 0 -> salvarProduto();
                case 1 -> buscarTodosProdutos();
                case 2 -> buscarProdutoPorId();
                case 3 -> atualizarProduto();
                case 4 -> excluirProduto();
                case 5 -> System.exit(0);
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
    }

    private static void exibirMenu() {
        System.out.println("\n### Menu de Operações ###");
        System.out.println("0. Salvar novo produto");
        System.out.println("1. Buscar todos produtos");
        System.out.println("2. Buscar produto por ID");
        System.out.println("3. Atualizar produto");
        System.out.println("4. Excluir produto");
        System.out.println("5. Sair do programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void salvarProduto() {
        System.out.println("\n### Criar Novo Produto ###");
System.out.println("Informe nome do produto: ");
        String nome = console.readLine();
        System.out.println("Informe a quantidade: ");
        Integer quantidade = Integer.parseInt(console.readLine());
        System.out.println("Informe o valor: ");
        double valor = Double.parseDouble(console.readLine());

        Produto produto = new Produto(nome, quantidade, valor);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.salvar(produto);
            System.out.println("Produto salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar produto: " + e.getMessage());
        }
        
   
      
    }
    private static void buscarTodosProdutos() {
        System.out.println("\n### Buscar Todos ###");

      ProdutoDAO produtoDao = new ProdutoDAO();
    try {
       List<Produto> produtos = produtoDao.buscarTodos();
       if (produtos != null) {
        System.out.println("Lista de Produtos:");
      for (Produto produto : produtos) {
        System.out.println("Nome: " + produto.nome() + ", Quantidade: " + produto.quantidade() + ", Valor: " + produto.valor());
      }
       }else {
        System.out.println("Nenhum produto encontrado.");
       }
    }catch (Exception e) {
        System.out.println("Erro ao buscar produtos: " + e.getMessage());}
    }

    private static void buscarProdutoPorId() {
        System.out.println("\n### Buscar Produto por ID ###");
 System.out.println("Informe o ID do produto: ");
 Long id= Long.parseLong(console.readLine());
       ProdutoDAO produtoDao = new ProdutoDAO();
        try {
            Produto produto = produtoDao.buscarPorId(id);
            if (produto != null) {
                System.out.println("Produto encontrado:");
                System.out.println("Nome: " + produto.nome() + ", Quantidade: " + produto.quantidade() + ", Valor: " + produto.valor());
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        }
       
    }

    private static void atualizarProduto() {
        System.out.println("\n### Atualizar Produto ###");
System.out.println("Informe o ID do produto que deseja atualizar: ");
 Long id= Long.parseLong(console.readLine());
       ProdutoDAO produtoDao = new ProdutoDAO();
        try {
            Produto produtoExistente = produtoDao.buscarPorId(id);
            if (produtoExistente != null) {
                System.out.println("Produto encontrado:");
                System.out.println("Nome : " + produtoExistente.nome() + ", Quantidade: " + produtoExistente.quantidade() + ", Valor: " + produtoExistente.valor());
                System.out.println("Informe o novo nome do produto: ");
                String novoNome = console.readLine();
                System.out.println("Informe a nova quantidade do produto: ");
                Integer novaQuantidade = Integer.parseInt(console.readLine());
                System.out.println("Informe o novo valor do produto: ");
                double novoValor = Double.parseDouble(console.readLine());

                Produto produtoAtualizado = new Produto(produtoExistente.id(), novoNome, novaQuantidade, novoValor);
              try {
                    produtoDao.Update(produtoAtualizado);
                    System.out.println("Produto atualizado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao atualizar produto: " + e.getMessage());
                }
               
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        }
     
    }

    private static void excluirProduto() {
        System.out.println("\n### Excluir Produto ###");
        
    System.out.println("Informe o ID do produto que deseja excluir: ");
    Long id= Long.parseLong(console.readLine());
        ProdutoDAO produtoDao = new ProdutoDAO();
            try {
                Produto produtoExistente = produtoDao.buscarPorId(id);
                if (produtoExistente != null) {
                    System.out.println("Produto encontrado:");
                    System.out.println("Nome : " + produtoExistente.nome() + ", Quantidade: " + produtoExistente.quantidade() + ", Valor: " + produtoExistente.valor());
                try {
                        produtoDao.Excluir(produtoExistente);
                        System.out.println("Produto excluído com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao excluir produto: " + e.getMessage());
                    }
                
                } else {
                    System.out.println("Produto não encontrado.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
            }
    }
}

