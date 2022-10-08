import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MetodosAuxiliares {

    public static void menuInicial(){
        System.out.println("Digite 1 para entrar no menu de veículos");
        System.out.println("Digite 2 para entrar no menu de clientes");
        System.out.println("Digite 3 para entrar no menu de locação");
        System.out.println("Digite 4 para entrar no menu de funcionários");
        System.out.println("Digite 9 para sair");
    }
    public static void menuVeiculos(){
        System.out.println("Digite 1 para registrar um veículo");
        System.out.println("Digite 2 para listar veículos disponíveis");
        System.out.println("Digite 3 para editar um veículo disponível");
        System.out.println("Digite 4 para excluir um veículo");
        System.out.println("Digite 5 para alterar a disponibilidade de um veículo cadastrado");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }
    public static void menuFuncionarios(){
        System.out.println("Digite 1 para registrar um funcionário");
        System.out.println("Digite 2 para listar funcionários disponíveis");
        System.out.println("Digite 3 para editar um funcionário disponível");
        System.out.println("Digite 4 para excluir um funcionário");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuClientes(){
        System.out.println("Digite 1 para registrar um cliente");
        System.out.println("Digite 2 para listar cliente disponíveis");
        System.out.println("Digite 3 para editar um cliente disponível");
        System.out.println("Digite 4 para excluir um cliente");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static void menuLocacao(){
        System.out.println("Digite 1 para registrar uma locação");
        System.out.println("Digite 2 para listar locação realizadas");
        System.out.println("Digite 3 para editar uma locação realizada");
        System.out.println("Digite 4 para excluir uma locação");
        System.out.println("Digite 9 para voltar ao menu anterior");
    }

    public static Cliente retornarClienteAPartirDeListaDeStrings(String [] valores){
        Contato contato = new Contato(valores[2].replaceAll("telefone=",""), valores[3].replaceAll("email=",""));
        Endereco endereco = new Endereco(valores[4].replaceAll("rua=","")
        ,valores[5].replaceAll("numero",""),valores[6].replaceAll("bairro=","")
                ,valores[7].replaceAll("cidade=",""),valores[8].replaceAll("estado=","")
        ,valores[9].replaceAll("cep",""),valores[10].replaceAll("complemento=",""));
        return new Cliente(valores[0].trim(), valores[1].trim(), contato, endereco);
    }

    public static Funcionario retornarFuncionarioAPartirDeListaDeStrings(String[] valores){
        int matricula = Integer.parseInt(valores[2].trim());
        return new Funcionario(valores[0].trim(), valores[1].trim(), matricula);
    }

    public static Veiculo retornarVeiculoAPartirDeListaDeStrings(String[] valores){
        int ano = Integer.parseInt(valores[3].strip());
        double km = Double.parseDouble(valores[4].strip());
        double preco = Double.parseDouble(valores[5].strip());
        Disponibilidade disp = Disponibilidade.valueOf(valores[6].trim().replaceAll("Disponibilidade.",""));
        return new Veiculo(valores[0].trim(), valores[1].trim(), valores[2].trim(), ano, km, preco, disp, valores[7].trim());
    }

    public static void salvarFuncionarios(FuncionarioManipulacao v){
        try{
            PrintWriter writer = new PrintWriter("funcionarios.txt");
            for (int i = 0; i < v.retornarLista().size();i++){
                writer.println(v.retornarLista().get(i).toStringParaLista());
            }
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
    }

    public static void salvarClientes(ClienteManipulacao c){
        try{
            PrintWriter writer = new PrintWriter("clientes.txt");
            for (int i = 0; i < c.retornarLista().size();i++){
                writer.println(c.retornarLista().get(i).toStringParaLista());
            }
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
    }

    public static void salvarVeiculos(VeiculoManipulacao v){
        try{
            PrintWriter writer = new PrintWriter("veiculos.txt");
            for (int i = 0; i < v.retornarLista().size();i++){
                writer.println(v.retornarLista().get(i).toStringParaLista());
            }
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado no caminho designado" + e.getMessage());
        }
    }

}
