import java.util.ArrayList;
import java.util.List;

public class ClienteManipulacao implements Cadastro <Cliente> {

    private List<Cliente> listaDeCliente;

    public ClienteManipulacao(){
        this.listaDeCliente = new ArrayList<>();
    }

    public void realizarCadastro(Cliente cliente){
        this.listaDeCliente.add(cliente);
    }

    public void removerCadastro(Integer indice){
        this.listaDeCliente.remove(indice.intValue());
    }

    public void editarCadastro (Integer indice, Cliente cliente){
        Cliente clienteProcurado = listaDeCliente.get(indice);
        clienteProcurado.setCpf(cliente.getCpf());
        clienteProcurado.setNome(cliente.getNome());
        clienteProcurado.setContato(new Contato());
        clienteProcurado.setEndereco(new Endereco());
    }

    public void consultarCadastro(){
        for(int i = 0; i< listaDeCliente.size(); i++){
            System.out.println("id=" + i + "|" + listaDeCliente.get(i));
        }
    }
    public List<Cliente> retornarLista(){
        return listaDeCliente;
    }

    public Cliente retornarClientePorIndice(Integer indice){
        return this.listaDeCliente.get(indice);
    }

    public Cliente retornarClientesPorCPF(String n){
       List<Cliente> x = this.listaDeCliente.stream()
                .filter(cpf -> cpf.getCpf().equals(n))
               .toList();
       Cliente na = x.get(0);
       return na;
    }

}
