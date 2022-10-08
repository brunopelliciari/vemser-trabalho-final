import java.util.ArrayList;
import java.util.List;

public class LocacaoManipulacao implements Cadastro<Locacao> {

    private List<Locacao> listaLocacao = new ArrayList<>();

    @Override
    public void realizarCadastro(Locacao locacao) {

        this.listaLocacao.add(locacao);
        locacao.getVeiculo().alterarDisponibilidade();
    }

    @Override
    public void removerCadastro(Integer indice) {
        this.listaLocacao.remove(indice.intValue());
        System.out.println("Cadastro de locação removido!");
    }

    @Override
    public void editarCadastro(Integer indice, Locacao locacao) {
        Locacao locacaoDesejada = listaLocacao.get(indice);
        locacaoDesejada.setDataLocacao(locacao.getDataLocacao());
        locacaoDesejada.setDataDevolucao(locacao.getDataDevolucao());
        locacaoDesejada.setValorLocacao(locacao.getValorLocacao());
        locacaoDesejada.setCliente(locacao.getCliente());
        locacaoDesejada.setVeiculo(locacao.getVeiculo());
    }

    @Override
    public void consultarCadastro() {
        for(int i = 0; i < listaLocacao.size(); i++){
            System.out.println("id=" + i + "|" + listaLocacao.get(i));
        }
    }

    public List<Locacao> retornarLista(){
        return listaLocacao;
    }

    public Locacao retornarLocacaoPorIndice(Integer indice){
        return this.listaLocacao.get(indice);
    }
}