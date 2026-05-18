/*
 * O que PesquisarProduto precisa fazer?
 *
 * i. retornar produtos por nome
 *
 *    SELECT * FROM produto
 *    WHERE nome_prod LIKE %?%
 *
 * ii. retornar produtos por categoria
 *    
 *    SELECT *
 *    FROM (categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
 *                    INNER JOIN produto ON (id_produto = id_prod)
 *    WHERE id_cat = ?
 *
 * iii. retornar produtos por nome e categoria
 *
 *    SELECT *
 *    FROM (categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
 *                    INNER JOIN produto ON (id_produto = id_prod)
 *    WHERE (id_cat = ?) AND (nome_prod LIKE %?%)
 *
 */
package ctl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PesquisarProdutoDao;
import model.Produto;

public class ctlPesquisarProduto {
  private List<Produto> produtos = new ArrayList<>();

  private void imprimirProduto() {
    System.out.println("| id_prod | nome_prod | descricao | material | marca | ativo | pwd_img |");
    for (Produto ref : this.produtos) {
      System.out.printf("| %d | %s | %s | %s | %s | %b | %s |\n",
        ref.getId(), ref.getNome(), ref.getDescricao(), ref.getMaterial(), ref.getMarca(), ref.isAtivo(), ref.getImagemPrincipalUrl());
    }
  }

  public boolean pesquisar (int idCategoria, String nome) throws SQLException {

    this.produtos.clear();
    PesquisarProdutoDao pesquisarProdutoDao = new PesquisarProdutoDao();

    if(nome != null && idCategoria > 0) {
      if(pesquisarProdutoDao.buscarPorNomeECategoria(nome, idCategoria, this.produtos)) {
        imprimirProduto();
        return true;
      }
      return false;
    } else if (nome != null) {
      if(pesquisarProdutoDao.buscarPorNome(nome, this.produtos)) {
        imprimirProduto();
        return true;
      }
      return false;
    } else if (idCategoria > 0) {
      if(pesquisarProdutoDao.buscarPorCategoria(idCategoria, this.produtos)) {
        imprimirProduto();
        return true;
      }
      return false;
    } else {
      return false;
    }
  }
}
