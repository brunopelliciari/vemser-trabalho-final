package service;

import exceptions.BancoDeDadosException;
import model.Cliente;
import repository.ClienteRepository;

public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService() {
        clienteRepository = new ClienteRepository();
    }

    public void adicionarCliente(Cliente cliente) {
        EnderecoService enderecoService = new EnderecoService();
        ContatoService contatoService = new ContatoService();
        try {
            if(!validarCliente(cliente)){
                throw new RuntimeException("Cliente inválido");
            }
            Cliente clienteAdicionado = clienteRepository.adicionar(cliente);
            System.out.println("Cliente adicinado com sucesso! " + clienteAdicionado);
            enderecoService.removerEnderecosOciosos();
            contatoService.removerContatosOciosos();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id) {
        try {
            boolean conseguiuRemover = clienteRepository.remover(id);
            System.out.println("Removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Cliente cliente) {
        try {
            boolean conseguiuEditar = clienteRepository.editar(id, cliente);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            clienteRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public int retornarIdContato(int id){
        return clienteRepository.retornarIndiceContatoPorIdCliente(id);
    }

    public int retornarIdEndereco(int id){
        return clienteRepository.retornarIndiceEnderecoPorIdCliente(id);
    }

    public boolean validarCliente (Cliente cliente){
        return cliente.getCpf().length() == 11;
    }
}
