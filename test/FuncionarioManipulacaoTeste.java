package teste;

import model.Funcionario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FuncionarioManipulacaoTeste {
    @Test
    public void deveAdicionarFuncionarioComSucesso() {
        //SETUP
        FuncionarioManipulacao funcionario = new FuncionarioManipulacao();

        //ACT
        funcionario.realizarCadastro(new Funcionario("Funcionário de Teste", "024-854-698-09", 0001));

        //ASSERT
        Assertions.assertEquals(funcionario.retornarLista().size(), 1);
    }

    @Test
    public void deveRemoverFuncionarioComSucesso(){
        //SETUP
        FuncionarioManipulacao funcionario = new FuncionarioManipulacao();
        funcionario.realizarCadastro(new Funcionario("Funcionário de Teste", "024-854-698-09", 0001));

        //ACT
        funcionario.removerCadastro(0);

        //ASSERT
        Assertions.assertEquals(funcionario.retornarLista().size(), 0);
    }
    @Test
    public void deveEditarFuncionarioComSucesso(){
        //SETUP
        FuncionarioManipulacao funcionario = new FuncionarioManipulacao();
        funcionario.realizarCadastro(new Funcionario("Funcionário de Teste", "024-854-698-09", 0001));

        //ACT
        Funcionario funcionario1 = new Funcionario("entidades.Funcionário teste", "new cpf", 0001);
        funcionario.editarCadastro(0, funcionario1);

        //ASSERT
        Assertions.assertArrayEquals(funcionario.retornarFuncionarioPorIndice(0).getCpf().toCharArray(), funcionario1.getCpf().toCharArray());
    }
}