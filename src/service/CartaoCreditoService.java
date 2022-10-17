package service;

import exceptions.BancoDeDadosException;
import model.CartaoCredito;
import repository.CartaoCreditoRepository;
import java.util.List;

public class CartaoCreditoService {
    private CartaoCreditoRepository cartaoCreditoRepository;

    public CartaoCreditoService() {
        cartaoCreditoRepository = new CartaoCreditoRepository();
    }


    public void adicionarCartao(CartaoCredito cartaoCredito) {
        try {
            CartaoCredito cartaoAdicionado = cartaoCreditoRepository.adicionar(cartaoCredito);
            System.out.println("cartão adicinado com sucesso! " + cartaoAdicionado);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    public void removerCartao(Integer id) {
        try {
            boolean conseguiuRemover = cartaoCreditoRepository.remover(id);
            System.out.println("cartão removido? " + conseguiuRemover + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void editarCartao(Integer id, CartaoCredito cartaoCredito) {
        try {
            boolean conseguiuEditar = cartaoCreditoRepository.editar(id, cartaoCredito);
            System.out.println("cartão editado? " + conseguiuEditar + "| com id=" + id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    public void listarCartoes() {
        try {
            List<CartaoCredito> listar = cartaoCreditoRepository.listar();
            listar.forEach(System.out::println);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }
}
