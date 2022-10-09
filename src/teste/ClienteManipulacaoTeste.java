package teste;

import entidades.Cliente;
import entidades.Contato;
import entidades.Endereco;
import manipulacao.ClienteManipulacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClienteManipulacaoTeste {
    Contato contato = new Contato("38929090", "teste@email.com");
    Endereco endereco = new Endereco("Rua teste", "213", "Bairro Teste", "Cidade teste"
            , "Estado teste", "Cep teste"
            ,"Complemento teste");

    @Test
    public void deveAdicionarClientesComSucesso(){
        //SETUP
        ClienteManipulacao c = new ClienteManipulacao();
        //ACT
        c.realizarCadastro(new Cliente("entidades.Cliente teste", "Cpf teste", contato, endereco));
        //ASSERT
        Assertions.assertEquals(c.retornarLista().size(), 1);
    }

    @Test
    public void deveRemocverCLienteComSucesso(){
        //SETUP
        ClienteManipulacao c = new ClienteManipulacao();
        c.realizarCadastro(new Cliente("entidades.Cliente teste", "Cpf teste", contato, endereco));



        //ACT
        c.removerCadastro(0);

        //ASSERT
        Assertions.assertEquals(c.retornarLista().size(), 0);

    }
}