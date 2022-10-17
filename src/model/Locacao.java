package model;

import exceptions.DatasInvalidasException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Locacao {

    private Integer idlocacao;

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

    public Locacao(LocalDate dataLocacao, LocalDate dataDevolucao,double valorLocacao, Cliente cliente, Veiculo veiculo, CartaoCredito cartaoCredito, Funcionario funcionario) throws DatasInvalidasException {
        if (dataDevolucao.isBefore(dataLocacao)) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.valorLocacao = valorLocacao;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.cartaoCredito = cartaoCredito;
        this.funcionario = funcionario;
    }
    public Integer getIdlocacao() {
        return idlocacao;
    }

    public void setIdlocacao(Integer idlocacao) {
        this.idlocacao = idlocacao;
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


    public Double getValorLocacao() {
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

    @Override
    public String toString() {
        return "----------------DADOS DA LOCAÇÃO----------------\n" +
                "Locacao{" +
                "id_locacao=" + idlocacao +
                ", dataLocacao=" + dataLocacao +
                ", dataDevolucao=" + dataDevolucao +
                ", valorLocacao=" + valorLocacao +
                "\n" + cliente +
                "\n" +
                "--------Dados do Veiculo--------\n"+
                veiculo +
                "\n" +
                "--------Dados do Cartão--------\n"+
                cartaoCredito +
                "\n" +
                "--------Dados do Funcionario--------\n"+
                funcionario +
                '}' +
                "\n----------------------------------------------------" +
                "\n\n";
    }
}