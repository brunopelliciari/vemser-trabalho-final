import java.time.LocalDate;

public class CartaoCredito {

    private String numero;
    private String bandeira;
    private LocalDate validade;
    private double limite;

    public CartaoCredito(){
    }

    public CartaoCredito(String numero, String bandeira, LocalDate validade) throws DatasInvalidasException{
        if(validade.isBefore(LocalDate.now())) {
            throw new DatasInvalidasException("Erro! ");
        }
        this.numero = numero;
        this.bandeira = bandeira;
        this.validade = validade;
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

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
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
}
