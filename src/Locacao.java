import java.time.Duration;
import java.time.LocalDate;

public class Locacao {

    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private double valorLocacao;
    private Cliente cliente;
    private Veiculo veiculo;
    private CartaoCredito cartaoCredito;

    public Locacao() {
    }

    public Locacao(LocalDate dataLocacao, LocalDate dataDevolucao, Cliente cliente, Veiculo veiculo, CartaoCredito cartaoCredito) throws DatasInvalidasException{
        if(dataDevolucao.isBefore(dataLocacao)) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.cartaoCredito = cartaoCredito;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public double calcularValorLocacao (){
        Duration d2 = Duration.between(this.getDataLocacao(), this.getDataDevolucao());
        return d2.toDays() * this.veiculo.getValorLocacao();
    }

    @Override
    public String toString() {
        return "dataLocacao= " + dataLocacao +
                ", dataDevolucao= " + dataDevolucao +
                ", cliente= " + cliente +
                ", veiculo= " + veiculo +
                ", cartaoCredito= " + cartaoCredito;
    }

    public String toStringParaLista() {
        return "" + getDataLocacao() + ", " + getDataDevolucao()  + ", " + getCliente().getNome() + ", " + getCliente().getCpf()
        + ", " + getVeiculo().getModelo() + ", " + getVeiculo().getPlaca() + ", " + getCartaoCredito();

    }
}