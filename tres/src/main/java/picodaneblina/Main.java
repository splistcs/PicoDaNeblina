package picodaneblina;

import java.sql.SQLException;
import ctl.ctlPesquisarProduto;

public class Main {
  public static void main(String[] args) throws SQLExcepton {
    System.out.println("Hello world!");

    /* Para restar o Pesquisar Produto*/
    System.out.println("====== Teste PESQUISAR PRODUTO ======");
    ctlPesquisarProduto ctlpp = new ctlPesquisarProduto();

    if (ctlpp.pesquisar(0, "banana")) {
      System.out.println("Lindo");
      if (ctlpp.pesquisar(1, "a")) {
        System.out.println("Banana");
        if (ctlpp.pesquisar(2, null)) {
          System.out.println("ASTAH MINHA VAI PACU");
        }
      }
    }
  }
}
