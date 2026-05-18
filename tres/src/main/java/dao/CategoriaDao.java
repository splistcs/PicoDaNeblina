package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.dbcon.AcessoPSQL;
import model.Categoria;
/*
 * Não sei se deveria ser o model ou ctl aqui...
 * Optei pelo model por ora.
 */

public class CategoriaDao {

  public boolean inserir(Categoria categoria) {
    String sql = "INSERT INTO categoria(nome_cat, slug, ativo, id_pai) " +
                " VALUES (?,?,?,?)";
    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      cmd.setString(1, categoria.getNome());
      cmd.setString(2, categoria.getSlug());
      cmd.setBoolean(3, categoria.isAtivo());
      cmd.setInt(4, categoria.getPai()); // Isso daqui vai dar ruim ainda! 17/05/MMXXVI

      if (cmd.executeUpdate() == 1) {
        con.commit();
        return true;
      } else {
        con.rollback();
        return false;
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }

  public boolean remover(Categoria categoria) { // Remover por ID
    String sql = "DELETE FROM categoria " +
                " WHERE (id_cat = ?)";
    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      cmd.setInt(1, categoria.getId());
      /*
       * Talvez fazer um switch aqui:
       * (1) -- por id
       * (2) -- por nome
       * ...
       *
       * ou transformar em uma função...
       */

      if (cmd.executeUpdate() == 1) {
        con.commit();
        return true;
      } else {
        con.rollback();
        return false;
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }
  
  public boolean atualizar(Categoria categoria, int coluna) {
    String sql = "UPDATE categoria";
    /* Talvez por um 
     * while(coluna != 0)
     * ...
     * switch(coluna % 4)
     * ...
     * coluna coluna = coluna / 4;
     */
    switch (coluna) {
      case 1:
        sql = sql + " SET slug = ?";
        break;
      case 2:
        sql = sql + " SET ativo = ?";
        break;
      case 3:
        sql = sql + " SET id_pai = ?";
        break;
      default:
        sql = sql + " SET nome_cat = ?";
        break;
    }
    sql = sql + " WHERE (id_cat = ?)";

    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);    

      // Veja nota acima!
      switch (coluna) {
      case 1:
        cmd.setString(1, categoria.getSlug());
        break;
      case 2:
        cmd.setBoolean(1, categoria.isAtivo());
        break;
      case 3:
        cmd.setInt(1, categoria.getPai());
        break;
      default:
        cmd.setString(1, categoria.getNome());
        break;
      }
      cmd.setInt(2, categoria.getId());

      if (cmd.executeUpdate() == 1) {
        con.commit();
        return true;
      } else {
        con.rollback();
        return false;
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }

  public boolean retornar(Categoria categoria, int id) {
    /*
     *  Pensei em isso funcionar como um ponteiro...
     *  Então o ctl Cat. tem os p* de modelo Cat.
     *  Ela manda o id e o p* de alocamento e pronto!
     */ 
    String sql = "SELECT * FROM categoria " +
                " WHERE (id_cat = ?)";
    Connection con = null;
    PreparedStatement cmd = null;
    ResultSet saida = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      cmd.setInt(1, id);

      saida = cmd.executeQuery();

      if (saida.next()) {
        /*obs. next() retornar true ou false dependendo se há próxima linha, bom saber...*/
        categoria.setId(saida.getInt("id_cat"));
        categoria.setNome(saida.getString("nome_cat"));
        categoria.setSlug(saida.getString("slug"));
        categoria.setAtivo(saida.getBoolean("ativo"));
        categoria.setPai(saida.getInt("id_pai"));
        return true;
      } else {
        return false;
      }
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }
}
