package view;

import exceptions.BancoDeDadosException;
import model.Funcionario;
import model.Veiculo;
import repository.FuncionarioRepository;
import repository.VeiculoRepository;
import service.FuncionarioService;

import java.util.List;

public class MainTeste {
    public static void main(String[] args) {

        FuncionarioService f = new FuncionarioService();

        f.listarFuncionarios();
    }
}
