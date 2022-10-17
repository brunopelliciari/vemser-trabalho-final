package tests;

import model.Cliente;
import model.Contato;
import model.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.ClienteService;

public class ClienteTest {

    @Test
    public void deveInvalidarDadosCliente (){
        //SETUP
        Endereco endereco = new Endereco("Rua do teste", "1234"
                , "Bairro do Teste", "Cidade do teste",
                "Estado do Teste", "12345678", "Ap 12");
        Contato contato = new Contato("37895625", "teste@email.com");
        Cliente cliente = new Cliente("Teste", "134295869", contato, endereco);
        ClienteService clienteService = new ClienteService();
        //ACT
        boolean resultado = clienteService.validarCliente(cliente);

        //ASSERT
        Assertions.assertFalse(resultado);
    }
}
