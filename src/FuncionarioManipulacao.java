import java.util.ArrayList;
import java.util.List;

public class FuncionarioManipulacao implements Cadastro <Funcionario>{

    private List<Funcionario> listaDeFuncionario;

    public FuncionarioManipulacao(){
        this.listaDeFuncionario = new ArrayList<>();
    }

    public void realizarCadastro(Funcionario funcionario){
        this.listaDeFuncionario.add(funcionario);
    }

    public void removerCadastro(Integer indice){
        this.listaDeFuncionario.remove(indice.intValue());
    }

    public void editarCadastro(Integer indice, Funcionario funcionario){
        Funcionario funcionarioProcurado = listaDeFuncionario.get(indice);
        funcionarioProcurado.setMatricula(funcionario.getMatricula());
        funcionarioProcurado.setCpf(funcionario.getCpf());
        funcionarioProcurado.setNome(funcionario.getNome());
    }

    public void consultarCadastro(){
        for(int i = 0; i< listaDeFuncionario.size(); i++){
            System.out.println("id=" + i + "|" + listaDeFuncionario.get(i));
        }
    }
    public List<Funcionario> retornarLista(){
        return listaDeFuncionario;
    }
}
