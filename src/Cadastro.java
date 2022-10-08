public interface Cadastro<T> {

    public void realizarCadastro(T obj);
    public void removerCadastro(Integer i);
    public void editarCadastro(Integer i, T obj);
    public void consultarCadastro();

}