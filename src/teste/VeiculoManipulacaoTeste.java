package teste;

import entidades.Veiculo;
import enums.Disponibilidade;
import manipulacao.VeiculoManipulacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class VeiculoManipulacaoTeste {

    @Test
    public void deveAdicionarVeiculoComSucesso() {
        //SETUP
        VeiculoManipulacao veiculo = new VeiculoManipulacao();

        //ACT
        veiculo.realizarCadastro(new Veiculo("Ford", "Mondeo", "Branco" , 2005, 52143.25, 125.35, Disponibilidade.DISPONIVEL, "PKK-2144" ));


        //ASSERT
        Assertions.assertEquals(veiculo.retornarLista().size(), 1);
    }

    @Test
    public void deveRemoverVeiculoPorPlacaComSucesso() {
        //SETUP
        VeiculoManipulacao veiculo = new VeiculoManipulacao();
        veiculo.realizarCadastro(new Veiculo("Ford", "Mondeo", "Branco" , 2005, 52143.25, 125.35, Disponibilidade.DISPONIVEL, "PKK-2144"));


        //ACT
        veiculo.removerCadastro(0);

        //ASSERT
        Assertions.assertEquals(veiculo.retornarLista().size(), 0);

    }
}