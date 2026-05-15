package model;

import java.math.BigDecimal;

public class ItemCarrinho {
    private int quantidade;
    private BigDecimal subtotal;
    private Sku sku;

    public ItemCarrinho() {
    }

    /*controle de quantidade */
    public void incrementarQuantidade(int quantidade){
        this.quantidade += quantidade;
        this.subtotal = this.subtotal.add(this.sku.getPreco().multiply(BigDecimal.valueOf(quantidade)));
    }

    public void decrementarQuantidade(int quantidade){
        if (quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            this.subtotal = this.subtotal.subtract(this.sku.getPreco().multiply(BigDecimal.valueOf(quantidade)));
        }
        else System.err.println("Falha ao decrementar quantidade, quantidade insuficiente");
    }

    /*calculo de valor total */
    public BigDecimal calcularValorTotal(){
        this.subtotal = this.sku.getPreco().multiply(BigDecimal.valueOf(this.quantidade));
        return this.subtotal;
    }

    /* getters e setters */
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }
}
