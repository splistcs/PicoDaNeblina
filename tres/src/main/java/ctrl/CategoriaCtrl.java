package ctrl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoriaDao;
import model.Categoria;

public class CategoriaCtrl {
  private List<Categoria> categorias = new ArrayList<>();

  public List<Categoria> getListCategoria() {
    return this.categorias;
  }

  public boolean buscarTodasCategoria() throws SQLException {
    CategoriaDao categoriaDao = new CategoriaDao();
    return categoriaDao.retornarTudo(this.categorias);
  }
}
