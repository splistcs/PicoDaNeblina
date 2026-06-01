package ctrl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SkuDao;
import model.Sku;
import model.Produto;

public class SkuCtrl {
  private ArrayList<Sku> skus = new ArrayList<>();

  public List<Sku> getListSku() {
    return this.skus;
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
