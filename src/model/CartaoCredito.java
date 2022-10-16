package model;
public class CartaoCredito {
    private int idCartaoCredito;
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
    public CartaoCredito(int id_cartao_credito) {
        this.id_cartao_credito = id_cartao_credito;
    }

    public int getIdCartaoCredito() {
        return idCartaoCredito;
    }

    public void setIdCartaoCredito(int idCartaoCredito) {
        this.idCartaoCredito = idCartaoCredito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BandeiraCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(BandeiraCartao tipoBandeira){
        bandeira = tipoBandeira;
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
        return "CartaoCredito{" +
                "idCartaoCredito=" + idCartaoCredito +
                ", numero='" + numero + '\'' +
                ", bandeira=" + bandeira +
                ", validade='" + validade + '\'' +
                ", limite=" + limite +
                '}';
    }
}