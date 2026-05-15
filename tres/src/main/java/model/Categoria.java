package model;

import java.util.ArrayList;

public class Categoria {
    private int id;
    private String nome;
    private String slug;
    private boolean ativo;
    private ArrayList<Categoria> subcategoriaList;
    private boolean isRaiz;

    public Categoria() {
    }

    public boolean isRaiz(){
        if (this.isRaiz){
            return true;
        }
        else return false;
    }

    /* controle de subcategorias */
    public void adicioarSubcategoria(Categoria categoria){
        this.subcategoriaList.add(categoria);
    }
    public void removerSubcategoria(Categoria categoria){
        this.subcategoriaList.remove(categoria);
    }
    public void getSubcategoria(int id){
        this.subcategoriaList.forEach(categoria -> {
            if (categoria.getId() == id){
                System.out.println("Categoria encontrada: " + categoria);
            }
        });
    }

    /* getters e setters */

    public void ativar(){
        this.ativo = true;
    }

    public void inativar(){
        this.ativo = false;
    }

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ArrayList<Categoria> getSubcategoriaList() {
        return subcategoriaList;
    }

    public void setSubcategoriaList(ArrayList<Categoria> subcategoriaList) {
        this.subcategoriaList = subcategoriaList;
    }

    public void setRaiz(boolean isRaiz) {
        this.isRaiz = isRaiz;
    }
    
}
