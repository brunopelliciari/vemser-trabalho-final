package service;

import model.Funcionario;
import model.Cadastro;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioManipulacao implements Cadastro<Funcionario> {

    private List<Funcionario> listaDeFuncionario;

    public FuncionarioManipulacao(){
        this.listaDeFuncionario = new ArrayList<>();
    }

    public void realizarCadastro(Funcionario funcionario){

        this.listaDeFuncionario.add(funcionario);
    }

    public void removerCadastro(Integer indice){

        this.listaDeFuncionario.remove(indice.intValue());
        System.out.println("Cadastro removido!");
    }

    public void editarCadastro(Integer indice, Funcionario funcionario){
        Funcionario funcionarioProcurado = listaDeFuncionario.get(indice);
        funcionarioProcurado.setMatricula(funcionario.getMatricula());
        funcionarioProcurado.setCpf(funcionario.getCpf());
        funcionarioProcurado.setNome(funcionario.getNome());
    }

    public void consultarCadastro(){
        for(int i = 0; i< listaDeFuncionario.size(); i++){
            System.out.println("id=" + i + "| " + listaDeFuncionario.get(i));
        }
        System.out.println();
    }
    public List<Funcionario> retornarLista(){
        return listaDeFuncionario;
    }

    public Funcionario retornarFuncionariosPorCPF(String cpf) {
        try {
            List<Funcionario> cpfProcurado = this.listaDeFuncionario.stream()
                    .filter(funcionario -> funcionario.getCpf().equals(cpf))
                    .toList();
            return cpfProcurado.get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Cpf do funcionário não encontrado. Erro na leitura de inicialização do banco de dados.");
            return null;
        }
    }

    public Funcionario retornarFuncionarioPorIndice(Integer indice){
        return this.listaDeFuncionario.get(indice);
    }
}
