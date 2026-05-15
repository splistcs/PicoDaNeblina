package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Enums.StatusPedido;

public class Pedido {
    private int id;
    private LocalDateTime dataPedido;
    private BigDecimal valorTotal;
    private BigDecimal valorFrete;
    private StatusPedido status;
    private ArrayList<ItemPedido> itemPedidoList;
    private EnderecoPedido enderecoEntrega;

    
    public Pedido() {
    }

    public void adicionarItemPedido(ItemPedido itemPedido){
        this.itemPedidoList.add(itemPedido);
    }
    public void removerItemPedido(ItemPedido itemPedido){
        this.itemPedidoList.remove(itemPedido);
    }
    public void getItemPedido(int id){
        this.itemPedidoList.forEach(itemPedido -> {
            if (itemPedido.getId() == id){
                System.out.println("ItemPedido encontrado: " + itemPedido);
            }
        });
    }

    public void alterarStatus(StatusPedido status){
        this.status = status;
    }

    public int calcularPrazoEntrega(){
        return 0; //<----------- ALTERAR!!!!
    }

    /*getters e setters */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public ArrayList<ItemPedido> getItemPedidoList() {
        return itemPedidoList;
    }

    public void setItemPedidoList(ArrayList<ItemPedido> itemPedidoList) {
        this.itemPedidoList = itemPedidoList;
    }

    public EnderecoPedido getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoPedido enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }
    
}
