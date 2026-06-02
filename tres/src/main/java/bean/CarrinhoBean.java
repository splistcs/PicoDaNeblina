package bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dao.CarrinhoDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.CarrinhoDeCompras;
import model.Cliente;
import model.ItemCarrinho;

@Named("CarrinhoBean")
@ViewScoped
public class CarrinhoBean {
    private int idSessao;
    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;
    private String cepDestino;
    private ArrayList<ItemCarrinho> itemCarrinhoList;
    
    
    public CarrinhoBean(){
    }

    public CarrinhoBean(Cliente cliente){
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        CarrinhoDao carrinhoDao = new CarrinhoDao();

        carrinhoDao.retornar(cliente.getId(), carrinho);
            this.idSessao = carrinho.getIdSessao();
            this.dataCriacao = carrinho.getDataCriacao();
            this.valorTotal = carrinho.getValorTotal();
            this.cepDestino = carrinho.getCepDestino();
            this.itemCarrinhoList = carrinho.getItemCarrinhoList();
    }

    public void inserir(Cliente cliente){
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.setIdSessao(this.idSessao);
        carrinho.setDataCriacao(this.dataCriacao);
        carrinho.setValorTotal(this.valorTotal);
        carrinho.setCepDestino(this.cepDestino);
        carrinho.setItemCarrinhoList(this.itemCarrinhoList);

        CarrinhoDao carrinhoDao = new CarrinhoDao();
        carrinhoDao.inserir(cliente.getId(), carrinho);
    }

    public void inserir(int idCliente){
        CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
        carrinho.setIdSessao(this.idSessao);
        carrinho.setDataCriacao(this.dataCriacao);
        carrinho.setValorTotal(this.valorTotal);
        carrinho.setCepDestino(this.cepDestino);
        carrinho.setItemCarrinhoList(this.itemCarrinhoList);

        CarrinhoDao carrinhoDao = new CarrinhoDao();
        carrinhoDao.inserir(idCliente, carrinho);
    }

    public void deletar(int idCliente){
        CarrinhoDao carrinhoDao = new CarrinhoDao();
        carrinhoDao.deletar(idCliente);
    }

    public void limparCarrinho(){
        this.itemCarrinhoList.clear();
    }

    public void calcularTotal(){
        this.valorTotal = BigDecimal.ZERO;
        this.itemCarrinhoList.forEach(itemCarrinho -> {
        this.valorTotal = this.valorTotal.add(itemCarrinho.getSubtotal());
        });
    }

    public void removerItem(ItemCarrinho itemCarrinho){
        this.itemCarrinhoList.remove(itemCarrinho);
        this.valorTotal = BigDecimal.ZERO;
        this.itemCarrinhoList.forEach(item -> {
            this.valorTotal = this.valorTotal.add(item.getSubtotal());
        } 
    }       


}
