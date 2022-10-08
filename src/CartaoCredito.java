import java.time.LocalDate;

public class CartaoCredito {

    private String numero;
    private BandeiraCartao bandeira;
    private LocalDate validade;
    private double limite;

    public CartaoCredito(){
    }

    public CartaoCredito(String numero, BandeiraCartao bandeira, LocalDate validade, double limite) throws DatasInvalidasException{
        if(validade.isBefore(LocalDate.now())) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.numero = numero;
        this.bandeira = bandeira;
        this.validade = validade;
        this.limite = limite;
    }

    public void consultarCartao(){
        System.out.println("\nDados do Cartão de Crédito"
                + "\nNúmero do cartão: "
                + this.numero
                + "\nBandeira do cartão: "
                + this.bandeira
                + "\nValidade do cartão: "
                + this.validade
                + "\nLimite do cartão: "
                + this.limite);
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
    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
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
                ", validade=" + validade +
                ", limite=" + limite;
    }
}