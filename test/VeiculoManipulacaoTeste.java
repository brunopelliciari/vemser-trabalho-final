package teste;

import model.Disponibilidade;
import exceptions.DatasInvalidasException;
import model.Veiculo;
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
    @Test
    public void deveEditarLocacaoComSucesso() throws DatasInvalidasException {
        //SETUP
        VeiculoManipulacao veiculo = new VeiculoManipulacao();
        veiculo.realizarCadastro(new Veiculo("Ford", "Mondeo", "Branco" , 2005, 52143.25, 125.35, Disponibilidade.DISPONIVEL, "PKK-2144"));
        Veiculo v = new Veiculo("Ford", "Ka" , "Preto" , 2009, 10000, 500, Disponibilidade.DISPONIVEL, "CHA-2144");
        //ACT
        veiculo.editarCadastro(0, v);
        //ASSERT
        Assertions.assertEquals(veiculo.retornarVeiculoPorIndice(0).getPlaca(), v.getPlaca());
    }
}