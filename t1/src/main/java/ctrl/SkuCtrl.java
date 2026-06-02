package ctrl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SkuDao;
import dao.ProdutoDao;
import model.Sku;
import model.Produto;

public class SkuCtrl {
  private List<Sku> skus = new ArrayList<>();

  public List<Sku> getListSku() {
    return this.skus;
  }

  public boolean completarProdutoSku (int id, Produto produto) throws SQLException {
    ProdutoDao produtoDao = new ProdutoDao();

    if(produto != null) {
      return produtoDao.retornar(produto, id);
    }
    return false;
  }

  public boolean pesquisarPorProduto (Produto produto) throws SQLException {
    this.skus.clear();

    SkuDao skuDao = new SkuDao();

    if(produto != null) {
      return skuDao.retornarTudoPorProduto(produto, this.skus);
    }
    return false;
  }
}
