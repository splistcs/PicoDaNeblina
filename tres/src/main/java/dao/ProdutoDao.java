package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.dbcon.AcessoPSQL;
import java.util.List;
import model.Produto;

/*
 * Não sei se deveria ser o model ou ctl aqui...
 * Optei pelo model por ora.
 */

public class ProdutoDao {
    
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

  public boolean inserir(Produto produto) {
    String sql = "INSERT INTO produto(nome_prod, descricao, material, marca, ativo, pwd_img) " +
                " VALUES (?,?,?,?,?,?)";
    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      cmd.setString(1, produto.getNome());
      cmd.setString(2, produto.getDescricao());
      cmd.setString(3, produto.getMaterial());
      cmd.setString(4, produto.getMarca());
      cmd.setBoolean(5, produto.isAtivo());
      cmd.setString(6, produto.getImagemPrincipalUrl());

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

  public boolean remover(Produto produto) { // Remover por ID
    String sql = "DELETE FROM produto " +
                " WHERE (id_prod = ?)";
    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      cmd.setInt(1, produto.getId());
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
  
  public boolean atualizar(Produto produto, int coluna) {
    String sql = "UPDATE produto";

    switch (coluna) {
      case 1:
        sql = sql + " SET descricao = ?";
        break;
      case 2:
        sql = sql + " SET material = ?";
        break;
      case 3:
        sql = sql + " SET marca = ?";
        break;
      case 4:
        sql = sql + " SET ativo = ?";
        break;
      case 5:
        sql = sql + " SET pwd_img = ?";
        break;
      default:
        sql = sql + " SET nome_prod = ?";
        break;
    }
    sql = sql + " WHERE (id_prod = ?)";

    Connection con = null;
    PreparedStatement cmd = null;

    try {
      con = AcessoPSQL.conectar();
      if (con == null) {
        return false;
      }
      con.setAutoCommit(false);

      cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);    

      switch (coluna) {
      case 1:
        cmd.setString(1, produto.getDescricao());
        break;
      case 2:
        cmd.setString(1, produto.getMaterial());
        break;
      case 3:
        cmd.setString(1, produto.getMarca());
        break;
      case 4:
        cmd.setBoolean(1, produto.isAtivo());
        break;
      case 5:
        cmd.setString(1, produto.getImagemPrincipalUrl());
        break;
      default:
        cmd.setString(1, produto.getNome());
        break;
      }
      cmd.setInt(2, produto.getId());

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

  public boolean retornar(Produto produto, int id) {
    /*
     *  Pensei em isso funcionar como um ponteiro...
     */ 
    String sql = "SELECT * FROM produto " +
                " WHERE (id_prod = ?);";
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
        produto.setId(saida.getInt("id_prod"));
        produto.setNome(saida.getString("nome_prod"));
        produto.setDescricao(saida.getString("descricao"));
        produto.setMaterial(saida.getString("material"));
        produto.setMarca(saida.getString("marca"));
        produto.setAtivo(saida.getBoolean("ativo"));
        produto.setImagemPrincipalUrl(saida.getString("pwd_img"));
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
