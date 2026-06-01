package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


import dao.dbcon.AcessoPSQL;
import model.CarrinhoDeCompras;

public class CarrinhoDao {

    private CarrinhoDeCompras formatarCarrinho(ResultSet saida) throws SQLException {
        CarrinhoDeCompras carrinhoAux = new CarrinhoDeCompras();

        carrinhoAux.setIdSessao(saida.getInt("id_car"));
        carrinhoAux.setValorTotal(saida.getBigDecimal("valor_total_car"));
        carrinhoAux.setDataCriacao(LocalDateTime.parse(saida.getString("data_car")));
        carrinhoAux.setValorTotal(saida.getBigDecimal("valor_total_car"));

        return carrinhoAux;
    }

    public boolean retornar(int idCliente, CarrinhoDeCompras resultado){
        String sql = "SELECT * FROM carrinho " +
                    " WHERE (id_cliente = ?);";
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
            cmd.setInt(1, idCliente);

            saida = cmd.executeQuery();

            /* 1 - Existe carrinho para tal cliente? */
            if (saida.next()) {
                /* Existe! */
                resultado = formatarCarrinho(saida);
                return true;
            } else {
                /* Não existe! */
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            AcessoPSQL.desconectar(con);
        }
    }

    public boolean inserir(int idCliente, CarrinhoDeCompras carrinho){
        String sql = "INSERT INTO carrinho (id_cliente, id_sessao, data_car, valor_total_car) " +
                    " VALUES (?, ?, ?, ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, idCliente);
            cmd.setInt(2, carrinho.getIdSessao());
            cmd.setString(3, carrinho.getDataCriacao().toString());
            cmd.setBigDecimal(4, carrinho.getValorTotal());

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

    public boolean atualizar(int idCliente, CarrinhoDeCompras carrinho){
        String sql = "UPDATE carrinho SET id_sessao = ?, data_car = ?, valor_total_car = ? " +
                    " WHERE (id_cliente = ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, carrinho.getIdSessao());
            cmd.setString(2, carrinho.getDataCriacao().toString());
            cmd.setBigDecimal(3, carrinho.getValorTotal());
            cmd.setInt(4, idCliente);

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

    public boolean deletar(int idCliente){
        String sql = "DELETE FROM carrinho " +
                    " WHERE (id_cliente = ?);";
        Connection con = null;
        PreparedStatement cmd = null;

        try {
            con = AcessoPSQL.conectar();
            if (con == null) {
                return false;
            }
            con.setAutoCommit(false);

            cmd = con.prepareStatement(sql);
            cmd.setInt(1, idCliente);

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
/*    
'   private int idSessao;
    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;
    private String cepDestino;
    private ArrayList<ItemCarrinho> itemCarrinhoList; */

/*CREATE TABLE carrinho (
  id_car            SERIAL,
  id_cliente        INT UNIQUE, -- Só pode ter um carrinho por id de cliente
  id_sessao         VARCHAR(21),
  data_car          DATE,
  valor_total_car   NUMERIC(12,2),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id),
  PRIMARY KEY (id_car)
); */