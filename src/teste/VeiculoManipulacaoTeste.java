package teste;

import entidades.*;
import enums.Disponibilidade;
import excecao.DatasInvalidasException;
import manipulacao.LocacaoManipulacao;
import manipulacao.VeiculoManipulacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


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
    @Test
    public void deveEditarLocacaoComSucesso() throws DatasInvalidasException {
        //SETUP
        VeiculoManipulacao veiculo = new VeiculoManipulacao();
        veiculo.realizarCadastro(new Veiculo("Ford", "Mondeo", "Branco" , 2005, 52143.25, 125.35, Disponibilidade.DISPONIVEL, "PKK-2144"));
        Veiculo v = new Veiculo("Ford", "Ka" , "Preto" , 2009, 10000, 500, Disponibilidade.DISPONIVEL, "CHA-2144");
        //ACT
        veiculo.editarCadastro(0, v);
        //ASSERT
        Assertions.assertTrue(veiculo.retornarVeiculoPorIndice(0).getPlaca().equals(
                v.getPlaca()));
    }
}