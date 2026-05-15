package model;

import java.util.ArrayList;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Sku> skuList;
    private String Material;
    private String Marca;
    private boolean ativo;
    private String imagemPrincipalUrl;

    public Produto() {
    }

    /*controle de sku*/

    public void adicionarSku(Sku sku){
        this.skuList.add(sku);
    }
    public void removerSku(Sku sku){
        this.skuList.remove(sku);
    }
    public void getSku(int id){
        this.skuList.forEach(sku -> {
            if (sku.getId() == id){
                System.out.println("Sku encontrado: " + sku);
            }
        });
    }

    public void ativar(){
    }
    
    public void inativar(){
    }

    /* getters e setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(ArrayList<Sku> skuList) {
        this.skuList = skuList;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagemPrincipalUrl() {
        return imagemPrincipalUrl;
    }

    public void setImagemPrincipalUrl(String imagemPrincipalUrl) {
        this.imagemPrincipalUrl = imagemPrincipalUrl;
    }
    
}
