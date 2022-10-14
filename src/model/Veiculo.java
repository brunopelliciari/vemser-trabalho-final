package model;

public class Veiculo {

    private int id_veiculo;
    private String marca;
    private String modelo;
    private String cor;
    private int ano;
    private double quilometragem;
    private double valorLocacao;
    private Disponibilidade disponibilidade;
    private String placa;

    public Veiculo(String marca, String modelo, String cor, int ano, double quilometragem, double valorLocacao
            , Disponibilidade disponibilidade, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.valorLocacao = valorLocacao;
        this.disponibilidade = disponibilidade;
        this.placa = placa;
    }

    public Veiculo(){

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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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

    public int getDisponibilidade() {
        return disponibilidade.getS();
    }

    public void alterarDisponibilidade(){
        if(disponibilidade.getS()==1){
            this.disponibilidade = Disponibilidade.DISPONIVEL;
        }
        else if(disponibilidade.getS()==2){
            this.disponibilidade = Disponibilidade.ALUGADO;
        }
    }

    public void setDisponibilidade(int i){
        if(i==1){
            this.disponibilidade = Disponibilidade.ALUGADO;
        }
        else if(i==2){
            this.disponibilidade = Disponibilidade.DISPONIVEL;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "[marca=" + marca +
                ", modelo=" + modelo +
                ", cor=" + cor +
                ", ano=" + ano +
                ", quilometragem=" + quilometragem +
                ", valorLocacao=" + valorLocacao +
                ", disponibilidade=" + disponibilidade +
                ", placa=" + placa + "]";
    }

    public String impressaoConversora() {
        return "" + getMarca() + ", " + getModelo() + ", " + getCor() + ", " + getAno()
                + ", " + getQuilometragem() + ", " + getValorLocacao() + ", " + disponibilidade + ", " + getPlaca();

    }
}
