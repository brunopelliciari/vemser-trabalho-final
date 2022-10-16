package model;

public class Veiculo {

    private Integer idVeiculo;
    private String marca;
    private String modelo;
    private String cor;
    private Integer ano;
    private Double quilometragem;
    private Double valorLocacao;
    private DisponibilidadeVeiculo disponibilidadeVeiculo;
    private String placa;

    public Veiculo(String marca, String modelo, String cor, Integer ano, Double quilometragem, Double valorLocacao
            , DisponibilidadeVeiculo disponibilidadeVeiculo, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.valorLocacao = valorLocacao;
        this.disponibilidadeVeiculo = disponibilidadeVeiculo;
        this.placa = placa;
    }
    public Veiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Veiculo(){
    }

    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Double quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(Double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Integer getDisponibilidadeVeiculo() {
        return disponibilidadeVeiculo.getDisponibilidade();
    }

    public void setDisponibilidadeVeiculo(DisponibilidadeVeiculo disponibilidade){
            this.disponibilidadeVeiculo = disponibilidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void alterarDisponibilidadeVeiculo(){
        if(this.disponibilidadeVeiculo.getDisponibilidade() == 1){
            this.disponibilidadeVeiculo = DisponibilidadeVeiculo.DISPONIVEL;
        }else if(this.disponibilidadeVeiculo.getDisponibilidade() == 2){
            this.disponibilidadeVeiculo = DisponibilidadeVeiculo.ALUGADO;
        }
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id_veiculo=" + idVeiculo +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cor='" + cor + '\'' +
                ", ano=" + ano +
                ", quilometragem=" + quilometragem +
                ", valorLocacao=" + valorLocacao +
                ", disponibilidadeVeiculo=" + disponibilidadeVeiculo +
                ", placa='" + placa + '\'' +
                '}';
    }
}
