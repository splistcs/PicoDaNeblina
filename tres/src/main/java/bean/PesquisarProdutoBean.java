package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import ctl.ctlPesquisarProduto;
import ctl.ctlCategoria;
import model.Categoria;
import model.Produto;

/* Engraçado enquanto não chamava nada do Model 1000 maravilha!
 * Foi só teimar um helloworld, JSF:
 *
 *  " Yo, yo no sè creo PesquisarProduto niguno."
 *
 *  Então tive que declarar:
 */
@Named("PesquisarProdutoBean")
@ViewScoped
public class PesquisarProdutoBean implements Serializable {

  private ctlPesquisarProduto ctlpp = new ctlPesquisarProduto();
  private ctlCategoria ctlctg = new ctlCategoria();
  private List<Produto> tmpListaProduto = new ArrayList<>();
  private List<Categoria> tmpListaCategoria = new ArrayList<>();
  /* Outra gambiarra... */
  private int tmpIdCategoria = 0;
  private String tmpTexto;    

  /* Carregar as categorias se não ela aparece vazia...
   *
   * Penso em talvez mandar direto no private.
   */
  @PostConstruct
  public void prepararCategoria() {
    try {
      if (ctlctg.buscarTodasCategoria()) {
        this.tmpListaCategoria = ctlctg.getListCategoria();
      }
    } catch (SQLException DeuRuim) {
      System.out.println("Maldito BEAN" + DeuRuim.getMessage());
    }
  }
  
  public List<Categoria> getTmpListaCategoria() {
    return this.tmpListaCategoria;
  }
 
  public int getTmpIdCategoria() {
    return this.tmpIdCategoria;
  }

  public void setTmpIdCategoria(int entrada) {
    this.tmpIdCategoria = entrada;
  }
 
  public String getTmpTexto() {
    return this.tmpTexto;
  }

  public void setTmpTexto(String entrada) {
    this.tmpTexto = entrada;
  }
 
  public void executarBusca() {
    try {
      if (ctlpp.pesquisar(this.tmpIdCategoria, this.tmpTexto)) {
        this.tmpListaProduto = ctlpp.getListProduto();
      }
    } catch (SQLException e) {
      System.out.println("Erro ao executar busca de produtos: " + e.getMessage());
    }
  }

  public List<Produto> getTmpListaProduto() {
    return this.tmpListaProduto;
  }
}
