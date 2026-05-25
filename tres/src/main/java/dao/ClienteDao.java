// CREATE TABLE cliente (
//   id              SERIAL,
//   nome_completo   VARCHAR(70),
//   email           VARCHAR(70),
//   telefone        VARCHAR(21),
//   cpf             VARCHAR(14),
//   senhaHash       VARCHAR(70),
//   ativo           BOOLEAN,
//   tipoCadastro    INT,  -- Aqui entra o ENUM de TipoCadastro
//   PRIMARY KEY (id)
// );
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.dbcon.AcessoPSQL;
import model.Cliente;
import model.Cadastro;
import model.Enums.TipoCadastro;

public class ClienteDao {

  /* Como economizamos espaço no db, optei por fazer um função que traduz do DB --> model */
  private Cliente formatarCliente(ResultSet saida) throws SQLException {
    Cliente clienteAux = new Cliente();
    Cadastro cadastroAux = new Cadastro();

    clienteAux.setId(saida.getInt("id"));
    clienteAux.setNomeCompleto(saida.getString("nome_completo"));
    clienteAux.setTelefone(saida.getString("telefone"));
    clienteAux.setCpf(saida.getString("cpf"));

    cadastroAux.setEmail(saida.getString("email"));
    cadastroAux.setSenhaHash(saida.getString("senhaHash"));
    cadastroAux.setAtivo(saida.getBoolean("ativo"));
    cadastroAux.setId(saida.getInt("id"));
    cadastroAux.setPapel(TipoCadastro.tipoCadastroInt(saida.getInt("tipoCadastro")));

    clienteAux.setCadastro(cadastroAux);
    return clienteAux;
  }

  public boolean fazerLoginEmail(String email, String senha, Cliente resultado) {
    String sql = "SELECT * FROM cliente " +
                " WHERE (email = ?);";
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
      cmd.setString(1, email);

      saida = cmd.executeQuery();

      /* 1 - Existe conta com tal email? */
      if (saida.next()) {
        /* Existe!
         * Agora a senha bate? */
        if(senha.equals(saida.getString("senhaHash"))) {
          /* Sim! */
          Cliente aux = this.formatarCliente(saida);
          resultado.setCadastro(aux.getCadastro());
          resultado.setCpf(aux.getCpf());
          resultado.setTelefone(aux.getTelefone());
          resultado.setNomeCompleto(aux.getNomeCompleto());
          resultado.setId(aux.getId());
          return true;
        }
      }
      
      return false;
    } catch (SQLException deuRuim) {
      System.out.println("ERRO" + deuRuim.getMessage());
      return false;
    } finally {
      AcessoPSQL.desconectar(con);
    }
  }
}
