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

  public List<Produto> getListProduto() {
    return this.produtos;
  }

  public boolean pesquisar (int idCategoria, String nome) throws SQLException {
    this.produtos.clear();
    PesquisarProdutoDao pesquisarProdutoDao = new PesquisarProdutoDao();

    if(nome != null && idCategoria > 0) {
      return pesquisarProdutoDao.buscarPorNomeECategoria(nome, idCategoria, this.produtos);
    } else if (nome != null) {
      return pesquisarProdutoDao.buscarPorNome(nome, this.produtos);
    } else if (idCategoria > 0) {
      return pesquisarProdutoDao.buscarPorCategoria(idCategoria, this.produtos);
    } else {
      return false;
    }
  }
}
