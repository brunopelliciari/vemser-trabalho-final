package model;

import model.BandeiraCartao;

public class CartaoCredito {

    private String numero;
    private BandeiraCartao bandeira;
    private String validade;
    private double limite;

    public CartaoCredito(){
    }

    public CartaoCredito(String numero, BandeiraCartao bandeira, String validade, double limite){
        this.numero = numero;
        this.bandeira = bandeira;
        this.validade = validade;
        this.limite = limite;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getBandeira() {
        return bandeira.getS();
    }

    public void setBandeira(int i){
        if(i==1){
            this.bandeira = BandeiraCartao.VISA;
        }
        else if(i==2){
            this.bandeira = BandeiraCartao.MASTERCARD;
        }
    }
    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "numero=" + numero +
                ", bandeira=" + bandeira +
                ", validade="+ validade  +
                ", limite=" + limite;
    }
}