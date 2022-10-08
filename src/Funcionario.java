public class Funcionario extends Pessoa{

    private int matricula;

    public Funcionario(String nome, String cpf, int matricula){
        super(nome, cpf);
        this.matricula = matricula;
    }
    public Funcionario(){

    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                " nome=" + getNome() +
                ", cpf=" + getCpf() +
                ", matricula=" + matricula +
                '}';
    }

    public String toStringParaLista() {
        return "" + getNome() + ", " + getCpf() + ", " + getMatricula();

    }
}
