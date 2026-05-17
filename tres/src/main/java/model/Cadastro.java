package model;

import model.Enums.TipoCadastro;

public class Cadastro {
    private long id;
    private String email;
    private boolean ativo;
    private String senhaHash;
    private TipoCadastro papel;

    public Cadastro(long id, String email, String senhaHash, TipoCadastro papel) {
        this.id = id;
        this.email = email;
        this.senhaHash = senhaHash;
        this.papel = papel;
    }

    /* getters e setters */
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenhaHash() {
        return this.senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public TipoCadastro getPapel() {
        return this.papel;
    }

    public void setPapel(TipoCadastro papel) {
        this.papel = papel;
    }

    
    
}
