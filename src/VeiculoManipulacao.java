import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class VeiculoManipulacao implements Cadastro<Veiculo> {

    private List<Veiculo> listaDeVeiculos;

    public VeiculoManipulacao() {
        this.listaDeVeiculos = new ArrayList<>();
    }

    public void realizarCadastro(Veiculo veiculo){
        this.listaDeVeiculos.add(veiculo);
    }

    public void removerCadastro(Integer indice){
        this.listaDeVeiculos.remove(indice.intValue());
    }

    public void editarCadastro(Integer indice, Veiculo veiculo){
        Veiculo veiculoProcurado = listaDeVeiculos.get(indice);
        veiculoProcurado.setAno(veiculo.getAno());
        veiculoProcurado.setCor(veiculo.getCor());
        veiculoProcurado.setMarca(veiculo.getMarca());
        veiculoProcurado.setModelo(veiculo.getModelo());
        veiculoProcurado.setQuilometragem(veiculo.getQuilometragem());
        veiculoProcurado.setValorLocacao(veiculo.getValorLocacao());
        veiculoProcurado.setPlaca(veiculo.getPlaca());
        veiculoProcurado.setDisponibilidade(veiculo.getDisponibilidade());
    }

    public void consultarCadastro(){
        for(int i = 0; i< listaDeVeiculos.size(); i++){
            System.out.println("id=" + i + "|" + listaDeVeiculos.get(i));
        }
    }

    public List<Veiculo> retornarLista() {
        return listaDeVeiculos;
    }

    public Veiculo retornarVeiculoPorIndice(Integer indice){
        return this.listaDeVeiculos.get(indice);
    }

    public Veiculo retornarVeiculoPorPlaca(String placa){
        try {
            List<Veiculo> placaProcurada = this.listaDeVeiculos.stream()
                    .filter(carro -> carro.getPlaca().equals(placa))
                    .toList();
            return placaProcurada.get(0);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Placa não encontrada. Erro na leitura de inicialização do banco de dados.");
            return null;
        }
    }
    public void consultarCadastroDisponivel(){
        AtomicInteger i = new AtomicInteger();
        this.listaDeVeiculos.stream()
                .filter(d -> d.getDisponibilidade() == 2)
                .forEach(p -> System.out.println("id="+ (i.getAndIncrement()) + p.toString()));
        }
    }

