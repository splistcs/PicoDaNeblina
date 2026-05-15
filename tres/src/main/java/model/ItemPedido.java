package model;

import java.math.BigDecimal;

public class ItemPedido {
    private int id;
    private Sku sku;
    private int quantidade;
    private BigDecimal precoUnitario;

    public ItemPedido() {
        this.quantidade = 0;
        this.precoUnitario = BigDecimal.ZERO;
    }

    public BigDecimal calcularPrecoTotal(){
        return this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }


    /* getters e setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = BigDecimal.valueOf(precoUnitario);
    }
}
