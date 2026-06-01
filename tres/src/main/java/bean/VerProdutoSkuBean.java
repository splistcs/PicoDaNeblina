package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import ctrl.SkuCtrl;
import model.Sku;
import model.Produto;

/* Não sei como passar o produto de Pesquisar para Ver 
 * Preciso descobrir... */

@Named("VerProdutoSkuBean")
@ViewScoped
public class VerProdutoSkuBean implements Serializable {

  private SkuCtrl skuCtrl = new SkuCtrl();
  private List<Sku> skusTmp = new ArrayList<>();
  private Produto produto = new Produto();

  public void prepararSku() {
    try {
      if (skuCtrl.pesquisarPorProduto(produto)) {
        this.skusTmp = skuCtrl.getListSku();
      }
    } catch (SQLException DeuRuim) {
      System.out.println("Maldito BEAN" + DeuRuim.getMessage());
    }
  }
  
  public List<Sku> getListSkuTmp() {
    return this.skusTmp;
  }

  public void setProduto(Produto entrada) {
    this.produto = entrada;
  }

  public Produto getProduto() {
    return this.produto;
  }
}
