public class Contato {

    private String telefone;
    private String email;

    public Contato(String telefone, String email) {
        this.telefone = telefone;
        this.email = email;
    }

    public Contato(){

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void consultarContato() {
        System.out.println("-----Contato-----");
        System.out.println("Telefone: " + telefone);
        System.out.println("Email:" + email);
    }

    @Override
    public String toString() {
        return "telefone=" + telefone  +
                ", email=" + email ;
    }
}