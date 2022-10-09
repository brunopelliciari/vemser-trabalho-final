import java.time.LocalDate;

public class CartaoCredito {

    private String numero;
    private BandeiraCartao bandeira;
    private String validade;
    private double limite;

    public CartaoCredito(){
    }

    public CartaoCredito(String numero, BandeiraCartao bandeira, String validade, double limite){
//        char [] c = validade.toCharArray();
//        if(c[0] != '1' || (c[0] == '1' && c[1] == '/')){
//            if(Integer.parseInt(validade.substring(2, c.length)) < LocalDate.now().getYear()){
//                throw new DatasInvalidasException("Erro! ");
//            }
//            else if(Integer.parseInt(validade.substring(2, c.length)) == LocalDate.now().getYear() &&
//                    Integer.parseInt(validade.substring(0, 1)) < LocalDate.now().getMonthValue()){
//                    throw new DatasInvalidasException("Erro! ");
//            }
//        }
//        else if(c[0] == '1' && c[1] != '/') {
//            if (Integer.parseInt(validade.substring(3, c.length)) < LocalDate.now().getYear()) {
//                throw new DatasInvalidasException("Erro! ");
//            } else if (Integer.parseInt(validade.substring(3, c.length)) == LocalDate.now().getYear() &&
//                    Integer.parseInt(validade.substring(0, 2)) < LocalDate.now().getMonthValue()) {
//                throw new DatasInvalidasException("Erro! ");
//            }
//        }
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