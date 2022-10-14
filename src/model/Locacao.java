package model;

import exceptions.DatasInvalidasException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao implements ImpressaoConversora {

    private int id_locacao;
    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private double valorLocacao;
    private Cliente cliente;
    private Veiculo veiculo;
    private CartaoCredito cartaoCredito;
    private Funcionario funcionario;

    public Locacao() {
    }
    public Locacao(LocalDate dataLocacao, LocalDate dataDevolucao, Cliente cliente, Veiculo veiculo, CartaoCredito cartaoCredito, Funcionario funcionario) throws DatasInvalidasException {
        if(dataDevolucao.isBefore(dataLocacao)) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.cartaoCredito = cartaoCredito;
        this.funcionario = funcionario;
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

        return this.cliente;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public double calcularValorLocacao (){
        Duration d2 = Duration.between(this.getDataLocacao().atStartOfDay(), this.getDataDevolucao().atStartOfDay());
        return d2.toDays() * this.veiculo.getValorLocacao();
    }

    @Override
    public String toString() {
        return "dataLocacao= " + dataLocacao.format(fmt) +
                ", dataDevolucao= " + dataDevolucao.format(fmt) +
                ", cliente= " + cliente +
                ", veiculo= " + veiculo +
                ", cartaoCredito= " + cartaoCredito +
                ", veiculo= " + veiculo +
                ", funcionário= " + funcionario;
    }

    public String impressaoConversora() {
        return "" + getDataLocacao() + ", " + getDataDevolucao()  + ", " + getCliente().getNome() + ", " + getCliente().getCpf()
                + ", " + getVeiculo().getModelo() + ", " + getVeiculo().getPlaca() + ", " + getCartaoCredito() + ", " + getFuncionario().getNome()
                + ", " + getFuncionario().getCpf();

    }
}