package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import bean.PesquisarProdutoBean;
import ctrl.SkuCtrl;
import model.Sku;
import model.Produto;

@Named("VerProdutoSkuBean")
@ViewScoped
public class VerProdutoSkuBean implements Serializable {

  private SkuCtrl skuCtrl = new SkuCtrl();
  private List<Sku> skusTmp = new ArrayList<>();
  private Sku sku = new Sku();
  private int skuId;
  private int produtoId;
  private int quantidade = 1;
  private Produto produto = new Produto();

  public void prepararSku() {
    try {
      if (produtoId > 0) {
        if (skuCtrl.completarProdutoSku(produtoId, this.produto)) {
          if (skuCtrl.pesquisarPorProduto(produto)) {
            this.skusTmp = skuCtrl.getListSku();
            if (skusTmp != null && !skusTmp.isEmpty()) {
                this.sku = skusTmp.get(0);
                this.skuId = this.sku.getId();
            }
          }
        }
      }
    } catch (SQLException DeuRuim) {
      System.out.println("Maldito BEAN" + DeuRuim.getMessage());
    }
  }

  public BigDecimal getPrecoTotal() {
    if (sku != null && sku.getPreco() != null) {
      /* BigDecimal multiplica por método */
      return sku.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }
    return BigDecimal.ZERO;
  }

  public void carregarSkuSelecionado() {
    for (Sku sku : skusTmp) {
      if (sku.getId() == this.skuId) {
        this.sku = sku;
        break;
      }
    }
  }
  
  public List<Sku> getListSkuTmp() {
    return this.skusTmp;
  }

  public void setSku(Sku entrada) {
    this.sku = entrada;
  }

  public Sku getSku() {
    return this.sku;
  }  

  public void setProduto(Produto entrada) {
    this.produto = entrada;
  }

  public Produto getProduto() {
    return this.produto;
  }  

  public void setProdutoId(int entrada) {
    this.produtoId = entrada;
  }

  public int getProdutoId() {
    return this.produtoId;
  }

  public void setQuantidade(int entrada) {
    this.quantidade = entrada;
  }

  public int getQuantidade() {
    return this.quantidade;
  }

  public int getSkuId() {
    return this.skuId;
  }

  public void setSkuId(int entrada) {
    this.skuId = entrada;
  }
}
