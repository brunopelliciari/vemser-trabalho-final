package model;

public class Funcionario extends Pessoa {

    private int id_funcionario;
    private int matricula;


    public Funcionario(String nome, String cpf, int matricula){
        super(nome, cpf);
        this.matricula = matricula;
    }
    public Funcionario(){

    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "nome=" + getNome() +
                ", cpf=" + getCpf() +
                ", matricula=" + matricula;
    }

}
