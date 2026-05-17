package model;

import java.util.ArrayList;

public class Cliente {
    private int id;
    private String nomeCompleto;
    private String cpf;
    private String telefone;
    private Cadastro cadastro;
    private ArrayList<Pedido> pedidoList;
    private ArrayList<Endereco> enderecoList;

    public Cliente(int id, String nomeCompleto, String cpf, String telefone, Cadastro cadastro, Endereco endereco) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cadastro = cadastro;
        this.enderecoList = new ArrayList<>();
        this.enderecoList.add(endereco);
        this.pedidoList = new ArrayList<>();
    }


    public void adicionarEndereco(Endereco endereco){
        this.enderecoList.add(endereco);
    }
    public void removerEndereco(Endereco endereco){
        this.enderecoList.remove(endereco);
    }
    public void getEndereco(int id){
        this.enderecoList.forEach(endereco -> {
            if (endereco.getId() == id){
                System.out.println("Endereco encontrado: " + endereco);
            }
        });
    }
    public void alterarEndereco(Endereco endereco){
        this.enderecoList.forEach(endereco1 -> {
            if (endereco1.getId() == endereco.getId()){
                endereco1 = endereco;
            }
        });
    }


    public void adicionarPedido(Pedido pedido){
        this.pedidoList.add(pedido);
    }
    public void removerPedido(Pedido pedido){
        this.pedidoList.remove(pedido);
    }
    public void getPedido(int id){
        this.pedidoList.forEach(pedido -> {
            if (pedido.getId() == id){
                System.out.println("Pedido encontrado: " + pedido);
            }
        });
    }

     /*getters e setters */


    /*getters*/

    public int getId() {
        return this.id;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Cadastro getCadastro() {
        return this.cadastro;
    }



    public ArrayList<Pedido> getPedidoList() {
        return this.pedidoList;
    }

        
}
