package ctrl;

import java.sql.SQLException;

import dao.ClienteDao;
import model.Cliente;

/*
 * O que precisa fazer?
 *
 * i. Receber um email e uma senha;
 * ii. Confirmar a senha e o email;
 *
 */

public class ClienteCtrl {
  private Cliente cliente = new Cliente();

  public Cliente getCliente() {
    return this.cliente;
  }

  public boolean confirmarSenha (String email, String senha) throws SQLException {

    this.cliente = new Cliente();
    ClienteDao clienteDao = new ClienteDao();

    if(email != null && senha != null ) {
      return clienteDao.fazerLoginEmail(email, senha, cliente);
    } else {
      return false;
    }
  }
}
