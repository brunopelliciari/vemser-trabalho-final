package service;

import exceptions.BancoDeDadosException;
import model.Endereco;
import repository.EnderecoRepository;

public class EnderecoService {
    private EnderecoRepository enderecoRepository;

    public EnderecoService() {
        enderecoRepository = new EnderecoRepository();
    }

    public void adicionarEndereco(Endereco endereco) {
        try {
            Endereco enderecoAdicionado = enderecoRepository.adicionar(endereco);
            System.out.println("Endereço adicinado com sucesso! " + enderecoAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id) {
        try {
            boolean conseguiuRemover = enderecoRepository.remover(id);
            System.out.println("removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void removerEnderecosOciosos() throws BancoDeDadosException {
        try {
            enderecoRepository.listarEnderecoSemVinculo()
                    .stream()
                    .forEach(enderecos -> remover(enderecos.getId_endereco()));
        }
        catch (BancoDeDadosException e){
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Endereco endereco) {
        try {
            boolean conseguiuEditar = enderecoRepository.editar(id, endereco);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            enderecoRepository.listar().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public int retornarId(){
        try{
            return enderecoRepository.retornarUltimoIdRegistrado();
        }
        catch (BancoDeDadosException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void listarSemVinculo() {
        try {
            enderecoRepository.listarEnderecoSemVinculo().forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
