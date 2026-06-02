package ctrl;

import java.math.BigDecimal;
import java.util.ArrayList;

import model.CarrinhoDeCompras;
import model.ItemCarrinho;
import model.Sku;

public class CarrinhoCtrl {
    public CarrinhoCtrl() {   
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
    
}
