package service;

import exceptions.BancoDeDadosException;
import model.Locacao;
import repository.LocacaoRepository;

import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class LocacaoService {
    private LocacaoRepository locacaoRepository;

    public LocacaoService() {
        locacaoRepository = new LocacaoRepository();
    }

    public void adicionarLocacao(Locacao locacao) {
        try {
            Locacao locacaoAdicionado = locacaoRepository.adicionar(locacao);
            System.out.println("locação adicinado com sucesso! " + locacaoAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void remover(Integer id) {
        try {
            boolean conseguiuRemover = locacaoRepository.remover(id);
            System.out.println("removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editar(Integer id, Locacao locacao) {
        try {
            boolean conseguiuEditar = locacaoRepository.editar(id, locacao);
            System.out.println("editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        try {
            List<Locacao> list = locacaoRepository.listar();
            list.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

}
