package bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import ctrl.CarrinhoCtrl;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.CarrinhoDeCompras;
import model.ItemCarrinho;

@Named("CarrinhoBean")
@ViewScoped
public class CarrinhoBean implements Serializable{
    private int idSessao; // id cliente deve existir previamente!!!!! Passar de lugar !!!!!!!!!
    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;
    private String cepDestino;
    private ArrayList<ItemCarrinho> itemCarrinhoList;

    CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
    private CarrinhoCtrl carrinhoCtrl = new CarrinhoCtrl();

    @PostConstruct
    public void prepararCarrinho() {
        CarrinhoBean carrinhoBean = carrinhoCtrl.getCarrinhoBean(this.idSessao);
        this.idSessao = carrinhoBean.getIdSessao();
        this.dataCriacao = carrinhoBean.getDataCriacao();
        this.valorTotal = carrinhoBean.getValorTotal();
        this.cepDestino = carrinhoBean.getCepDestino();
        this.itemCarrinhoList = carrinhoBean.getItemCarrinhoList();
    }

    public void refresh(){
        this.itemCarrinhoList = carrinhoCtrl.getItemCarrinhoList(carrinho);
    }

    public int getIdSessao() {
        return idSessao;
    }
    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getCepDestino() {
        return cepDestino;
    }
    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public ArrayList<ItemCarrinho> getItemCarrinhoList() {
        return itemCarrinhoList;
    }
    public void setItemCarrinhoList(ArrayList<ItemCarrinho> itemCarrinhoList) {
        this.itemCarrinhoList = itemCarrinhoList;
    }

    public void addItemCarrinho(ItemCarrinho itemCarrinho) {
        this.itemCarrinhoList.add(itemCarrinho);
    }
    public void removeItemCarrinho(ItemCarrinho itemCarrinho) {
        this.itemCarrinhoList.remove(itemCarrinho);
    }


}
