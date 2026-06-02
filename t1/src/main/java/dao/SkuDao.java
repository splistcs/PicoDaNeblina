/*
CREATE TABLE produto (
  id_prod     SERIAL,
  nome_prod   VARCHAR(70),
  descricao   VARCHAR(210),
  material    VARCHAR(70),
  marca       VARCHAR(70),
  ativo       BOOLEAN,
  pwd_img     VARCHAR(100),
  PRIMARY KEY (id_prod)
);

CREATE TABLE sku (
  id_sku    SERIAL,
  id_prod   INT,
  estoque   INT,
  preco     NUMERIC(12,2),
  peso      INT,
  cod_uni   VARCHAR(100),
  alt_cm    INT,
  larg_cm   INT,
  compr_cm  INT,
  FOREIGN KEY (id_prod) REFERENCES produto (id_prod),
  PRIMARY KEY (id_sku)
);

-- hashMap especificacao, minha gambiarra criar uma nova tabela.
-- no postgrep existe o JSON ou Array, mas como são tam. fixo talvez de ruim.
CREATE TABLE especificacao (
  id_hp   INT,
  var     VARCHAR(70),
  valor   VARCHAR(70),
  FOREIGN KEY (id_hp) REFERENCES sku (id_sku),
  PRIMARY KEY (id_hp, var)
);
*/

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import dao.dbcon.AcessoPSQL;
import model.Sku;
import model.Produto;

public class SkuDao {

  private Sku formatarSku(ResultSet saida) throws SQLException {
    Sku aux = new Sku();
    aux.setId(saida.getInt("id_sku"));
    aux.setEstoque(saida.getInt("estoque"));
    aux.setPreco(saida.getBigDecimal("preco"));
    aux.setPesoGramas(saida.getInt("peso"));
    aux.setCodigoUniversal(saida.getString("cod_uni"));
    aux.setAlturaCm(saida.getInt("alt_cm"));
    aux.setLarguraCm(saida.getInt("larg_cm"));
    aux.setComprimentoCm(saida.getInt("compr_cm"));
    return aux;
  }

  public boolean retornarEspecificacaoPorSkuId(Connection con, Sku sku) throws SQLException {   
    String sql = "SELECT *  "
               + "FROM especificacao " 
               + "WHERE (id_hp = ?)";
    ResultSet saida = null;

    if (con == null) {
        return false;
    }

    try (PreparedStatement cmd = con.prepareStatement(sql)) {
      cmd.setInt(1, sku.getId());
      saida = cmd.executeQuery();

      while (saida.next()) {
        sku.getEspecificacoes().put(saida.getString("var"), saida.getString("valor"));
      }

      return !sku.getEspecificacoes().isEmpty();

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    }
  }

  public boolean retornarTudoPorProduto (Produto produto, List<Sku> skulist) {    
    String sql = "SELECT *  "
               + "FROM sku "
               + "WHERE (id_prod = ?)";

    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, produto.getId());
      saida = cmd.executeQuery();

      while(saida.next()) {
        Sku skuAux = formatarSku(saida);
        if(retornarEspecificacaoPorSkuId(con, skuAux)) {
          skulist.add(skuAux);
        }
      }

      return !skulist.isEmpty();

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }
}
