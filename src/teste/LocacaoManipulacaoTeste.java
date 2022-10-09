package teste;

import entidades.*;
import enums.Disponibilidade;
import excecao.DatasInvalidasException;
import manipulacao.FuncionarioManipulacao;
import manipulacao.LocacaoManipulacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;


public class LocacaoManipulacaoTeste {

    @Test
    public void deveCriarNovaLocacaoComSucesso() throws DatasInvalidasException {
        //SETUP
        Cliente cliente = new Cliente("", "", new Contato(), new Endereco());
        Veiculo veiculo = new Veiculo("", "", "", 2022, 52.20, 122.3, Disponibilidade.DISPONIVEL, "");
        CartaoCredito cartaoCredito = new CartaoCredito();
        LocalDate data1 = LocalDate.of(2022, 8, 15);
        LocalDate data2 = LocalDate.of(2022, 8, 25);
        Funcionario func = new Funcionario("Testador", "9848934893-10", 1);
        Locacao locacao1 = new Locacao(data1, data2, cliente, veiculo, cartaoCredito, func);


        //ACT
        LocacaoManipulacao locacao = new LocacaoManipulacao();
        locacao.realizarCadastro(locacao1);

        //ASSERT
        Assertions.assertEquals(locacao.retornarLista().size(), 1);
    }

    @Test
    public void deveRemoverLocacaoComSucesso() throws DatasInvalidasException {
        //SETUP
        Cliente cliente = new Cliente("", "", new Contato(), new Endereco());
        Veiculo veiculo = new Veiculo("", "", "", 2022, 52.20, 122.3, Disponibilidade.DISPONIVEL, "");
        CartaoCredito cartaoCredito = new CartaoCredito();
        LocalDate data1 = LocalDate.of(2022, 8, 15);
        LocalDate data2 = LocalDate.of(2022, 8, 25);
        Funcionario func = new Funcionario("Testador", "9848934893-10", 1);
        Locacao locacao1 = new Locacao(data1, data2, cliente, veiculo, cartaoCredito, func);

        LocacaoManipulacao locacao = new LocacaoManipulacao();
        locacao.realizarCadastro(locacao1);

        //ACT
        locacao.removerCadastro(0);

        //ASSERT
        Assertions.assertEquals(locacao.retornarLista().size(), 0);
    }

    @Test
    public void deveEditarLocacaoComSucesso() throws DatasInvalidasException {
        //SETUP
        Cliente cliente = new Cliente("", "", new Contato(), new Endereco());
        Veiculo veiculo = new Veiculo("", "", "", 2022, 52.20, 122.3, Disponibilidade.DISPONIVEL, "");
        CartaoCredito cartaoCredito = new CartaoCredito();
        LocalDate data1 = LocalDate.of(2022, 8, 15);
        LocalDate data2 = LocalDate.of(2022, 8, 25);
        Funcionario func = new Funcionario("Testador", "9848934893-10", 1);
        Locacao locacao1 = new Locacao(data1, data2, cliente, veiculo, cartaoCredito, func);
        Funcionario func2 = new Funcionario("TestadorX", "95959-10", 2);


        LocacaoManipulacao locacao = new LocacaoManipulacao();
        locacao.realizarCadastro(locacao1);

        //ACT
        Locacao locacao2 = new Locacao(data1, data2, cliente, veiculo, cartaoCredito, func2);
        locacao.editarCadastro(0, locacao2);
        //ASSERT
        Assertions.assertArrayEquals(locacao.retornarLocacaoPorIndice(0).getFuncionario().getCpf().toCharArray(),
                func2.getCpf().toCharArray());
    }
}