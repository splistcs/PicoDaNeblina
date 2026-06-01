package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CarrinhoDeCompras {
    private int idSessao;
    private LocalDateTime dataCriacao;
    private BigDecimal valorTotal;
    private String cepDestino;
    private ArrayList<ItemCarrinho> itemCarrinhoList;
    
    
    public CarrinhoDeCompras() {
    }
    public CarrinhoDeCompras(int idSessao, ItemCarrinho itemCarrinho){
        this.idSessao = idSessao;
        this.dataCriacao = LocalDateTime.now();
        this.itemCarrinhoList = new ArrayList<>();
        this.itemCarrinhoList.add(itemCarrinho);
    }
    public CarrinhoDeCompras(int idSessao, Sku sku, int quantidade){
        this.idSessao = idSessao;
        this.dataCriacao = LocalDateTime.now();
        this.itemCarrinhoList = new ArrayList<>();
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setSku(sku);
        itemCarrinho.setQuantidade(quantidade);
        itemCarrinho.calcularValorTotal();
        this.itemCarrinhoList.add(itemCarrinho);
    }

    /* controle de itens */
    public void adicionarItem(Sku sku, int quantidade){
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setSku(sku);
        itemCarrinho.setQuantidade(quantidade);
        itemCarrinho.calcularValorTotal();
        this.itemCarrinhoList.add(itemCarrinho);
    }
    public void removerItem(Sku sku){
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setSku(sku);
        this.itemCarrinhoList.remove(itemCarrinho);
    }
    public void alterarQuantidade(Sku sku, int quantidade){
        this.itemCarrinhoList.forEach(itemCarrinho -> {
            if (itemCarrinho.getSku().equals(sku)){
                itemCarrinho.setQuantidade(quantidade);
                itemCarrinho.calcularValorTotal();
            }
        });
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


    /* getters e setters */
    public int getIdSessao() {
        return this.idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getValorTotal() {
        return this.valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCepDestino() {
        return this.cepDestino;
    }

    public void setCepDestino(String cepDestino) {
        this.cepDestino = cepDestino;
    }

    public ArrayList<ItemCarrinho> getItemCarrinhoList() {
        return this.itemCarrinhoList;
    }

    public void setItemCarrinhoList(ArrayList<ItemCarrinho> itemCarrinhoList) {
        this.itemCarrinhoList = itemCarrinhoList;
    }

    
}
