package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import ctrl.PesquisarProdutoCtrl;
import ctrl.CategoriaCtrl;
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

  private PesquisarProdutoCtrl pesquisarProdutoCtrl = new PesquisarProdutoCtrl();
  private CategoriaCtrl categoriaCtrl = new CategoriaCtrl();
  private List<Produto> produtosTmp = new ArrayList<>();
  private List<Categoria> categoriasTmp = new ArrayList<>();
  /* Outra gambiarra... */
  private int IdCategoriaTmp = 0;
  private String textoTmp;    

  /* Carregar as categorias se não ela aparece vazia...
   *
   * Penso em talvez mandar direto no private.
   */
  @PostConstruct
  public void prepararCategoria() {
    try {
      if (CategoriaCtrl.buscarTodasCategoria()) {
        this.categoriasTmp = CategoriaCtrl.getListCategoria();
      }
    } catch (SQLException DeuRuim) {
      System.out.println("Maldito BEAN" + DeuRuim.getMessage());
    }
  }
  
  public List<Categoria> getListCategoriaTmp() {
    return this.categoriasTmp;
  }
 
  public int getIdCategoriaTmp() {
    return this.IdCategoriaTmp;
  }

  public void setIdCategoriaTmp(int entrada) {
    this.IdCategoriaTmp = entrada;
  }
 
  public String getTextoTmp() {
    return this.textoTmp;
  }

  public void setTextoTmp(String entrada) {
    this.textoTmp = entrada;
  }
 
  public void executarBusca() {
    try {
      if (pesquisarProdutoCtrl.pesquisar(this.IdCategoriaTmp, this.textoTmp)) {
        this.produtosTmp = pesquisarProdutoCtrl.getListProduto();
      }
    } catch (SQLException DeuRuim) {
      System.out.println("Erro ao executar busca de produtos: " + DeuRuim.getMessage());
    }
  }

  public List<Produto> getListProdutoTmp() {
    return this.produtosTmp;
  }
}
