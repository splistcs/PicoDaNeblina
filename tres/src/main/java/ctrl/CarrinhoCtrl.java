package ctrl;

import java.math.BigDecimal;
import java.util.ArrayList;

import bean.CarrinhoBean;
import dao.CarrinhoDao;
import model.CarrinhoDeCompras;
import model.ItemCarrinho;
import model.Sku;

public class CarrinhoCtrl {
    public CarrinhoCtrl() {   
    }

    public CarrinhoDeCompras FormatarBean(CarrinhoBean carrinhoBean) {
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.setIdSessao(carrinhoBean.getIdSessao());
        carrinho.setDataCriacao(carrinhoBean.getDataCriacao());
        carrinho.setValorTotal(carrinhoBean.getValorTotal());
        carrinho.setCepDestino(carrinhoBean.getCepDestino());
        carrinho.setItemCarrinhoList(carrinhoBean.getItemCarrinhoList());
        return carrinho;
    }

    public CarrinhoDeCompras getCarrinho(int idSessao) {
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinhoDao.retornar(idSessao, carrinho);
        return carrinho;
    }

    public boolean inserirCarrinho(CarrinhoDeCompras carrinho, int idSessao) {
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        return carrinhoDao.inserir(idSessao, carrinho);
    }
    public boolean inserirCarrinho(CarrinhoBean carrinhoBean, int idSessao) {
        CarrinhoDeCompras carrinho = FormatarBean(carrinhoBean);
        return inserirCarrinho(carrinho, idSessao);
    }


    public boolean atualizarCarrinho(CarrinhoDeCompras carrinho, int idSessao) {
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        return carrinhoDao.atualizar(idSessao, carrinho);
    }
    public boolean atualizarCarrinho(CarrinhoBean carrinhoBean, int idSessao) {
        CarrinhoDeCompras carrinho = FormatarBean(carrinhoBean);
        return atualizarCarrinho(carrinho, idSessao);
    }

    public boolean deletarCarrinho(int idSessao) {
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        return carrinhoDao.deletar(idSessao);
    }
    public boolean deletarCarrinho(CarrinhoBean carrinhoBean, int idSessao) {
        return deletarCarrinho(idSessao);
    }

    public void inserirProduto(ArrayList<ItemCarrinho> itemCarrinhoList, Sku sku, int quantidade) {
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setSku(sku);
        itemCarrinho.setQuantidade(quantidade);
        itemCarrinho.calcularValorTotal();
        itemCarrinhoList.add(itemCarrinho);
    }
    
    public void removerProduto(ArrayList<ItemCarrinho> itemCarrinhoList, ItemCarrinho itemCarrinho) {
        itemCarrinhoList.remove(itemCarrinho);
    }

    public void atualizarQuantidade(ArrayList<ItemCarrinho> itemCarrinhoList, ItemCarrinho itemCarrinho, int novaQuantidade) {
        itemCarrinho.setQuantidade(novaQuantidade);
        itemCarrinho.calcularValorTotal();
    }

    public ArrayList<ItemCarrinho> getItemCarrinhoList(CarrinhoDeCompras carrinho) {
        return carrinho.getItemCarrinhoList();
    }

    public BigDecimal calcularValorTotal(ArrayList<ItemCarrinho> itemCarrinhoList) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemCarrinho item : itemCarrinhoList) {
            valorTotal = valorTotal.add(item.getSubtotal());
        }
        return valorTotal;
    }

    public BigDecimal calcularValorTotal(CarrinhoDeCompras carrinho) {
        return calcularValorTotal(carrinho.getItemCarrinhoList());
    }
    
    public CarrinhoBean getCarrinhoBean(int idSessao){
        CarrinhoBean carrinhoBean = new CarrinhoBean();
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinhoDao.retornar(idSessao, carrinho);
        
        carrinhoBean.setIdSessao(carrinho.getIdSessao());
        carrinhoBean.setDataCriacao(carrinho.getDataCriacao());
        carrinhoBean.setValorTotal(calcularValorTotal(carrinho));
        carrinhoBean.setCepDestino(carrinho.getCepDestino());
        carrinhoBean.setItemCarrinhoList(carrinho.getItemCarrinhoList());

        return carrinhoBean;
    }
}
