package bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import ctrl.ClienteCtrl;
import model.Cliente;
import model.Cadastro;
import model.Enums.TipoCadastro;

@Named("FazerLoginBean")
@SessionScoped
public class FazerLoginBean implements Serializable {

  private ClienteCtrl clienteCtrl = new ClienteCtrl();
  private String emailTmp;
  private String senhaTmp;
  private String nomeTmp;
  private TipoCadastro tipoCadastroTmp;

  public void setEmailTmp(String entrada) {
    this.emailTmp = entrada;
  }

  public String getEmailTmp() {
    return this.emailTmp;
  }

  public void setSenhaTmp(String entrada) {
    this.senhaTmp = entrada;
  }

  public String getSenhaTmp() {
    return this.senhaTmp;
  }

  public String executarLogin() {
    try {
      if(clienteCtrl.confirmarSenha(emailTmp, senhaTmp)) {
        nomeTmp = clienteCtrl.getCliente().getNomeCompleto();
        tipoCadastroTmp = clienteCtrl.getCliente().getCadastro().getPapel();
        return "/pesquisarproduto?faces-redirect=true";
      } else {
        /* https://showcase.primefaces.org/ui/message/growl.xhtml?jfwid=46741 
         * Copiei de lá...
         */
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ops...", "Tente outra vez"));
      }
    } catch (SQLException DeuRuim) {
      System.out.println("ERRO: Login" + DeuRuim.getMessage());
    }

    return null;
  }

  public TipoCadastro getTipoCadastroTmp() {
    return this.tipoCadastroTmp;
  }

  public String getNomeTmp() {
    return this.nomeTmp;
  }  

  public void setNomeTmp(String entrada) {
    this.nomeTmp = entrada;
  }
}
