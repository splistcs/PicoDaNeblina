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

    public Cliente() { }

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

     /*getters e setters
      *
      * "Onde estão meus setters, Varius Icarus? Oh, Icarus, meus setters..."
      * - Imperator Maximus Roverius, após o relato de Linus Pompeu da campanha na Astah.
      *
      *   Em MMXXVI, os três setters sob Varius Icarus e a quinta dao de Crassus Chus 
      *   foram massacrados na floresta da antiga Astah contra as forças de Armirus Edsonus,
      *   restaurador do Secundum Sacrum Imperium Astahnum.
      *
      *   Historiadores investigam a consequência desse evento no Idos de Maio e coup de grâce de Roverius
      *   para a criação da República Java e eleição dos cônsules Linus Pompeu e Brutus Murakami, a qual terminou
      *   com a ascensão da ditadura de Murakami e a "guerra enterna dos dois segundos" na Astah.
      *   Que culminou no colapso do reinado de Armirus Edsonus...
      *
      *   No fim uma questão continua aberta... ONDE ESTÂO OS SETTERS?
      */
    /*getters*/

    public int getId() {
        return this.id;
    }

    public void setId(int entrada) {
        this.id = entrada;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String entrada) {
        this.nomeCompleto = entrada;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String entrada) {
        this.cpf = entrada;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String entrada) {
        this.telefone = entrada;
    }

    public Cadastro getCadastro() {
        return this.cadastro;
    }

    public void setCadastro(Cadastro entrada) {
        this.cadastro = entrada;
    }

    public ArrayList<Pedido> getPedidoList() {
        return this.pedidoList;
    }
}
