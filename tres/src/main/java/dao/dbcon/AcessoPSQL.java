package dao.dbcon;

/* 
 * Isso copiei e adaptei do exemplo de ling. prog. 1.
 *
 * Precisa testar no computador do ifsp, se a conexão funciona.
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AcessoPSQL {
  private static final String DATABASE = "pdn";
  private static final String HOST = "localhost:5432";
  private static final String DRIVER = "org.postgresql.Driver";
  private static final String URL = "jdbc:postgresql://" + HOST + "/" + DATABASE;
  private static final String USR = "postgres";
  private static final String PWD = "ifsp";
  
  public static Connection conectar() {
    try {
      Class.forName(DRIVER);                        
      return DriverManager.getConnection(URL, USR, PWD);
    } catch (ClassNotFoundException | SQLException e) {
      System.err.println("ERRO" + e.getMessage());
      return null;
    }
  }

  public static void desconectar(Connection con) {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException e) {
      System.err.println("ERRO");
    }
  }
}
