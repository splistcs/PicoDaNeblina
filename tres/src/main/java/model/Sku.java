package model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Sku {
    private int id;
    private int estoque;
    private BigDecimal preco;
    private HashMap<String, String> especificacoes;
    private int pesoGramas;
    private String codigoUniversal;
    private int alturaCm;
    private int larguraCm;
    private int comprimentoCm;

    public boolean isDisponivel(){
        if (this.estoque > 0){
            return true;
        }
        else return false;
    }

    public Sku() {
    }

    /* funções de crud de especificação */
    public void adicionarEspecificacao(String chave, String valor){
        this.especificacoes.put(chave, valor);
    }
    public void removerEspecificacao(String chave){
        this.especificacoes.remove(chave);
    }
    public void getEspecificacao(String chave){
        this.especificacoes.get(chave);
    }
    public void listEspecificacao(){
        this.especificacoes.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
    }
    public void atualizarEspecificacao(String chave, String valor){
        if(this.especificacoes.get(chave) != null){
            this.especificacoes.replace(chave, valor);
        }
        else System.err.println("Falha ao encontrar o elemento de chave" + chave);
    }
    
    /* funções de gestão de estoque */
    public void reservarEstoque(int quantidade){
        if (quantidade <= this.estoque){
            this.estoque -= quantidade;
        }
        else System.err.println("Falha ao reservar estoque, quantidade insuficiente");
    }
    public void estornarEstoque(int quantidade){
        this.estoque += quantidade;
    }


    /* getters e setters */
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public HashMap<String, String> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(HashMap<String, String> especificacoes) {
        this.especificacoes = especificacoes;
    }

    public int getPesoGramas() {
        return pesoGramas;
    }

    public void setPesoGramas(int pesoGramas) {
        this.pesoGramas = pesoGramas;
    }

    public String getCodigoUniversal() {
        return codigoUniversal;
    }

    public void setCodigoUniversal(String codigoUniversal) {
        this.codigoUniversal = codigoUniversal;
    }

    public int getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(int alturaCm) {
        this.alturaCm = alturaCm;
    }

    public int getLarguraCm() {
        return larguraCm;
    }

    public void setLarguraCm(int larguraCm) {
        this.larguraCm = larguraCm;
    }

    public int getComprimentoCm() {
        return comprimentoCm;
    }

    public void setComprimentoCm(int comprimentoCm) {
        this.comprimentoCm = comprimentoCm;
    } 
}
