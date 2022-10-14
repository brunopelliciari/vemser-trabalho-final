package service;

import model.Cliente;
import model.Cadastro;

import java.util.ArrayList;
import java.util.List;

public class ClienteManipulacao implements Cadastro<Cliente> {

    private List<Cliente> listaDeCliente;

    public ClienteManipulacao(){
        this.listaDeCliente = new ArrayList<>();
    }

    public void realizarCadastro(Cliente cliente){

        this.listaDeCliente.add(cliente);
    }

    public void removerCadastro(Integer indice){

        this.listaDeCliente.remove(indice.intValue());
        System.out.println("Cadastro removido!");
    }

    public void editarCadastro (Integer indice, Cliente cliente){
        Cliente clienteProcurado = listaDeCliente.get(indice);
        clienteProcurado.setCpf(cliente.getCpf());
        clienteProcurado.setNome(cliente.getNome());
        clienteProcurado.setContato(cliente.getContato());
        clienteProcurado.setEndereco(cliente.getEndereco());
    }

    public void consultarCadastro(){
        for(int i = 0; i< listaDeCliente.size(); i++){
            System.out.println("id=" + i + "|" + listaDeCliente.get(i));
        }
        System.out.println();
    }
    public List<Cliente> retornarLista(){
        return listaDeCliente;
    }

    public Cliente retornarClientePorIndice(Integer indice){
        return this.listaDeCliente.get(indice);
    }

    public Cliente retornarClientesPorCPF(String cpf){
        try {
            List<Cliente> cpfProcurado = this.listaDeCliente.stream()
                    .filter(cliente -> cliente.getCpf().equals(cpf))
                    .toList();
            return cpfProcurado.get(0);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Cpf não encontrado. Erro na leitura de inicialização do banco de dados.");
            return null;
        }
    }

}
