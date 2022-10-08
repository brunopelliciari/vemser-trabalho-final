public class Cliente extends Pessoa{

    private Contato contato;
    private Endereco endereco;

    public Cliente(String nome, String cpf, Contato contato, Endereco endereco){
        super(nome, cpf);
        this.contato = contato;
        this.endereco = endereco;
    }

    public Cliente(){

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
        return "Cliente{" +
                " nome=" + getNome() +
                ", cpf=" + getCpf() +
                ", contato=" + contato +
                ", endereco=" + endereco +
                '}';
    }

    public String toStringParaLista() {
        return "" + getNome() + ", " + getCpf() + ", " + getContato() + ", " + getEndereco();

    }
}
