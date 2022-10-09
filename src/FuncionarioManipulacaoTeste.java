
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
}