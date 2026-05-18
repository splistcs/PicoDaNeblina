package model;

import java.util.ArrayList;
import model.Sku;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Sku> skuList;
    private ArrayList<Categoria> categoriaList;
    private String material;
    private String marca;
    private boolean ativo;
    private String imagemPrincipalUrl;

    /* Melhor ter um construtor vazio só para teste!*/
    public Produto() { };

    public Produto(Sku sku){
        this.skuList = new ArrayList<>();
        this.skuList.add(sku);
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

    /* controle de categorias */
    public void adicionarCategoria(Categoria categoria){
        this.categoriaList.add(categoria);
    }
    public void removerCategoria(Categoria categoria){
        this.categoriaList.remove(categoria);
    }
    public void getCategoria(int id){
        this.categoriaList.forEach(categoria -> {
            if (categoria.getId() == id){
                System.out.println("Categoria encontrada: " + categoria);
            }
        });
    }

    public void ativar(){
        this.ativo = true;
    }
    
    public void inativar(){
        this.ativo = false;
    }

    /* getters e setters */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ArrayList<Sku> getSkuList() {
        return this.skuList;
    }

    public void setSkuList(ArrayList<Sku> skuList) {
        this.skuList = skuList;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getImagemPrincipalUrl() {
        return this.imagemPrincipalUrl;
    }

    public void setImagemPrincipalUrl(String imagemPrincipalUrl) {
        this.imagemPrincipalUrl = imagemPrincipalUrl;
    }
}
