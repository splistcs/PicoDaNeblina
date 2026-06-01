package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.dbcon.AcessoPSQL;
import model.ItemCarrinho;


/*
CREATE TABLE item_carrinho (
  id_item_car SERIAL,
  id_car      INT,
  id_sku      INT,
  quant       INT,
  FOREIGN KEY (id_car) REFERENCES carrinho (id_car),
  FOREIGN KEY (id_sku) REFERENCES sku (id_sku),
  PRIMARY KEY (id_item_car)
); */

import model.Sku;

public class ItemCarrinhoDao {

    public ItemCarrinho formatarItemCarrinho(ResultSet saida) throws SQLException {
        ItemCarrinho itemCarrinhoAux = new ItemCarrinho();

        itemCarrinhoAux.setQuantidade(saida.getInt("quant"));
        Sku skuAux = new Sku();
        skuAux.setId(saida.getInt("id_sku"));
        itemCarrinhoAux.setSku(skuAux);

        return itemCarrinhoAux;
     }

    public boolean inserir(int idCarrinho, ItemCarrinho itemCarrinho){
        String sql = "INSERT INTO item_carrinho (id_car, id_sku, quant) " +
                    " VALUES (?, ?, ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, idCarrinho);
            cmd.setInt(2, itemCarrinho.getSku().getId());
            cmd.setInt(3, itemCarrinho.getQuantidade());

            int linhasAfetadas = cmd.executeUpdate();
            if (linhasAfetadas > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            AcessoPSQL.desconectar(con);
        }
      }

      public boolean atualizar(int idItemCarrinho, ItemCarrinho itemCarrinho){
        String sql = "UPDATE item_carrinho SET id_sku = ?, quant = ? " +
                    " WHERE (id_item_car = ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, itemCarrinho.getSku().getId());
            cmd.setInt(2, itemCarrinho.getQuantidade());
            cmd.setInt(3, idItemCarrinho);

            int linhasAfetadas = cmd.executeUpdate();
            if (linhasAfetadas > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            AcessoPSQL.desconectar(con);
        }
      }

      public boolean deletar(int idItemCarrinho){
        String sql = "DELETE FROM item_carrinho WHERE (id_item_car = ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, idItemCarrinho);

            int linhasAfetadas = cmd.executeUpdate();
            if (linhasAfetadas > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            AcessoPSQL.desconectar(con);
        }
  }
}
