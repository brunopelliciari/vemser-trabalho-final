package model;

public class Cliente extends Pessoa {

    private int id_cliente;
    private Contato contato;
    private Endereco endereco;

    public Cliente(String nome, String cpf, Contato contato, Endereco endereco){
        super(nome, cpf);
        this.contato = contato;
        this.endereco = endereco;
    }

    public Cliente(){

    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return  "[------Cliente------" + "\n" +
                " Id_cliente=" + id_cliente + "\n" +
                " Nome=" + getNome() + "\n" +
                " Cpf=" + getCpf() + "\n" +
                "------Contato------" + "\n"
                + " Id_cliente associado a este contato=" + id_cliente + "\n" +
                contato + "\n" +
                "------Endereco------" + "\n"
                + " Id_cliente associado a este endereço=" + id_cliente + "\n" +
                endereco + "]";
    }
}
