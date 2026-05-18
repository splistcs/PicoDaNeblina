package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.con.AcessoPSQL;
import model.Produto;

public class PesquisarProdutoDao {

  /*Uma ideia muito boa... ao invés de ter vários desse no código*/
  private Produto formatarProduto(ResultSet saida) throws SQLException {
    Produto aux = new Produto();
    aux.setId(saida.getInt("id_prod"));
    aux.setNome(saida.getString("nome_prod"));
    aux.setDescricao(saida.getString("descricao"));
    aux.setMaterial(saida.getString("material"));
    aux.setMarca(saida.getString("marca"));
    aux.setAtivo(saida.getBoolean("ativo"));
    aux.setImagemPrincipalUrl(saida.getString("pwd_img"));
    return aux;
  }

  public boolean buscarPorNome(String nome, List<Produto> resultado) {
    String sql = "SELECT * FROM produto " +
                 "WHERE nome_prod LIKE ?";

    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setString(1, "%" + nome + "%");
      saida = cmd.executeQuery();

      while (saida.next()) {
        resultado.add(formatarProduto(saida));
      }

    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
    return true;
  }

  /* Provavelmente teremos que mudar isso, pois só permite filtrar por 1 categoria */
  public boolean buscarPorCategoria(int idCategoria, List<Produto> resultado) {
    String sql = "SELECT p.* FROM produto p " +
                 "INNER JOIN tem_categoria ON p.id_prod = id_produto " +
                 "WHERE id_categoria = ?";
    /*obs. 
     * Por que está produto p? E o resto sem referência?
     * Ora, uma gambiarra é claro! (na verdade não...)
     * Queremos que só retorne as colunas de produto, mas como é JOIN,
     * a tabela do SELECT tem o id de categoria...
     *
     * Poderia filtrar e tratar isso na saída? Sim, mas...
     * Se o SQL consegue tratar que ele trate pois!
     */
    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, idCategoria);
      saida = cmd.executeQuery();

      while (saida.next()) {
        resultado.add(formatarProduto(saida));
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
    return true;
  }
  
  public boolean buscarPorNomeECategoria(String nome, int idCategoria, List<Produto> resultado) {
    String sql = "SELECT p.* FROM produto p " +
                 "INNER JOIN tem_categoria ON p.id_prod = id_produto " +
                 "WHERE id_categoria = ? AND p.nome_prod LIKE ?";

    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql);
      cmd.setInt(1, idCategoria);
      cmd.setString(2, "%" + nome + "%");
      saida = cmd.executeQuery();

      while (saida.next()) {
        resultado.add(formatarProduto(saida));
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
    return true;
  }
}
