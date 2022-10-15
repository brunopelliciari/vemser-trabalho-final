package service;

import exceptions.BancoDeDadosException;
import model.Funcionario;
import repository.FuncionarioRepository;

import java.util.List;

public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        funcionarioRepository = new FuncionarioRepository();
    }

    // criação de um objeto
    public void adicionarFuncionario(Funcionario funcionario) {
        try {

            if (funcionario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Funcionario funcionarioAdicionado = funcionarioRepository.adicionar(funcionario);
            System.out.println("funcionario adicinado com sucesso! " + funcionarioAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerFuncionario(Integer id) {
        try {
            boolean conseguiuRemover = funcionarioRepository.remover(id);
            System.out.println("funcionario removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarFuncionario(Integer id, Funcionario funcionario) {
        try {
            boolean conseguiuEditar = funcionarioRepository.editar(id, funcionario);
            System.out.println("funcionario editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public void listarFuncionarios() {
        try {
            List<Funcionario> listar = funcionarioRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
