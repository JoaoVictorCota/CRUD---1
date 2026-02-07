package br.com.joaocota;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) throws Exception {
        var sql1 = "INSERT INTO produto (nome, quantidade, valor) VALUES (?, ?, ?)";

        try (var conexao = Conexao.obterConnection();
             var stmt = conexao.prepareStatement(sql1)) {

            stmt.setString(1, produto.nome());
            stmt.setInt(2, produto.quantidade());
            stmt.setDouble(3, produto.valor());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Erro ao salvar produto", e);
        }
    }

    public List<Produto> buscarTodos() throws Exception {
        var sql1 = "SELECT * FROM produto";
        List<Produto> produtos = new ArrayList<>();

        try (var conexao = Conexao.obterConnection();
             var stmt = conexao.prepareStatement(sql1);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valor")
                );
                produtos.add(produto);
            }

        } catch (Exception e) {
            throw new Exception("Erro ao buscar produtos", e);
        }

        return produtos;
    }
     
     public Produto buscarPorId(Long id) throws Exception{

        var sql = "select * from produto where id = ?";

        Produto produto = null;

        try (var conexao = Conexao.obterConnection();
            var stmt = conexao.prepareStatement(sql)) {
                stmt.setLong(1, id);
                try(ResultSet rs = stmt.executeQuery()){
                    while (rs.next()) {
                        produto = new Produto(rs.getLong("id"), 
                        rs.getString("nome"), rs.getInt("quantidade"), 
                        rs.getDouble("valor"));
                    }
                }

        } catch (Exception e) {
            throw new Exception(e);
        }

        return produto;
    }

 public void Update(Produto produto) throws Exception {
        var sql1 = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? where id = ? ";

        try (var conexao = Conexao.obterConnection();
             var stmt = conexao.prepareStatement(sql1)) {

            stmt.setString(1, produto.nome());
            stmt.setInt(2, produto.quantidade());
            stmt.setDouble(3, produto.valor());
            stmt.setLong(4, produto.id());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Erro ao salvar produto", e);
        }
    }
     public void Excluir(Produto produto) throws Exception {
        var sql1 = "DELETE FROM produto WHERE id = ?";

        try (var conexao = Conexao.obterConnection();
             var stmt = conexao.prepareStatement(sql1)) {

            stmt.setLong(1, produto.id());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new Exception("Erro ao excluir produto", e);
        }
    }
    
}

