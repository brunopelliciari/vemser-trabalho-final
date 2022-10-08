import java.time.LocalDate;

public class Locacao {

    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private double quilometragem;
    private double valorLocacao;
    private boolean devolvido;
//    private Cliente cliente;
//    private Veiculo veiculo;
    private CartaoCredito cartaoCredito;

    public Locacao() {
    }

    public Locacao(LocalDate dataLocacao, LocalDate dataDevolucao/*, Cliente cliente, Veiculo veiculo*/) throws DatasInvalidasException{
        if(dataDevolucao.isBefore(dataLocacao)) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = false;
//        this.cliente = cliente;
//        this.veiculo = veiculo;
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

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public boolean getDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }

//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public Veiculo getVeiculo() {
//        return veiculo;
//    }
//
//    public void setVeiculo(Veiculo veiculo) {
//        this.veiculo = veiculo;
//    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public void consultarLocacao(){
        System.out.println("\nDados da locação"
                + "\nData de locação: "
                + this.getDataLocacao()
                + "\nData da devolução: "
                + this.getDataDevolucao()
                + "\nQuilometragem do veículo: : "
                + this.getQuilometragem()
                + "\nValor da locação: "
                + this.getValorLocacao()
                + "\nDevolvido: "
                + this.getDevolvido()
                + "\nCliente: ");
//                + this.getCliente
//                + "\nVeículo: "
//                + this.getVeiculo);
    }
}
