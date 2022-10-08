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
        c.realizarCadastro(new Cliente("Cliente teste", "Cpf teste", contato, endereco));
        //ASSERT
        Assertions.assertEquals(c.retornarLista().size(), 1);
    }
}
